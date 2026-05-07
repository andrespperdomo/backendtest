package com.inventory.application.usecase;

import org.jboss.logging.Logger;
import com.inventory.application.command.UpdateInventoryCommand;
import com.inventory.domain.exception.InventoryNotFoundException;
import com.inventory.domain.model.Inventory;
import com.inventory.domain.outbox.OutboxStatus;
import com.inventory.domain.outbox.OutboxType;
import com.inventory.domain.repository.InventoryRepository;
import com.inventory.domain.repository.OutboxRepository;
import com.inventory.infrastructure.rabbitmq.event.InventoryUpdatedEvent;
import com.inventory.infrastructure.rabbitmq.model.OutboxEvent;
import com.inventory.shared.utils.JsonUtil;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class UpdateInventoryUseCase {

    private static final Logger LOG = Logger.getLogger(UpdateInventoryUseCase.class);

    private final InventoryRepository repository;

    private final OutboxRepository outboxRepository;

    @Inject
    public UpdateInventoryUseCase(InventoryRepository productRepository, OutboxRepository outboxRepository) {
        this.repository = productRepository;
        this.outboxRepository = outboxRepository;
    }

    public Inventory execute(UpdateInventoryCommand command) {
        LOG.infof("Inventory updated | productId=%s quantity=%d", command.idProduct(),
                String.valueOf(command.quantity()));
        // 1. Build domain object (NO ID from command)
        Inventory inventory = repository.findById(Long.valueOf(command.idProduct()))
                .orElseThrow(() -> new InventoryNotFoundException(command.idProduct()));

        // business logic INSIDE domain
        inventory.decrease(command.quantity());

        // 3. Persist updated state
        Inventory updated = repository.update(inventory);

        // 4. Save OUTBOX EVENT
        saveOutboxEvent(command, updated);
        return updated;

    }

    // =========================
    // OUTBOX EVENT CREATION
    // =========================
    private void saveOutboxEvent(UpdateInventoryCommand command, Inventory inventory) {

        InventoryUpdatedEvent event = buildEvent(command);

        OutboxEvent outboxEvent = new OutboxEvent(command.idProduct(), OutboxType.INVENTORY_UPDATED.name(),
                JsonUtil.toJson(event),
                OutboxStatus.PENDING.name(), 0);

        outboxRepository.save(outboxEvent);
    }

    // =========================
    // EVENT BUILDER
    // =========================
    private InventoryUpdatedEvent buildEvent(UpdateInventoryCommand command) {
        return new InventoryUpdatedEvent(
                command.idProduct(),
                command.quantity(),
                "STOCK_DECREASE");
    }

}
