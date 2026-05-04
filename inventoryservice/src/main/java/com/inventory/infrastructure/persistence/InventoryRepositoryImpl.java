package com.inventory.infrastructure.persistence;

import java.util.List;
import java.util.Optional;
import com.inventory.domain.model.Inventory;
import com.inventory.domain.model.InventoryEntityMapper;
import com.inventory.domain.repository.InventoryRepository;
import com.inventory.shared.utils.PageResult;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class InventoryRepositoryImpl implements InventoryRepository {

    @Inject
    EntityManager em;

    @Override
    @Transactional
    public Inventory update(Inventory inventory) {

        // InventoryEntity entity = InventoryEntityMapper.toEntity(product);
        InventoryEntity entity = em.find(InventoryEntity.class, inventory.id());
        if (entity != null) {
            entity = em.merge(entity);
        }

        return InventoryEntityMapper.toDomain(entity);
    }

    @Override
    public Optional<Inventory> findById(Long id) {

        InventoryEntity entity = em.find(InventoryEntity.class, id);

        return Optional.ofNullable(entity)
                .map(InventoryEntityMapper::toDomain);
    }

    /*
     * @Override
     * public List<Product> findAll() {
     * 
     * List<ProductEntity> entities = em
     * .createQuery("FROM ProductEntity", ProductEntity.class)
     * .getResultList();
     * 
     * return entities.stream()
     * .map(ProductEntityMapper::toDomain)
     * .collect(Collectors.toList());
     * }
     */

    /*
     * public PageResult<Product> findAllPage(String search, int page, int size) {
     * 
     * page = Math.max(page, 0);
     * size = Math.min(Math.max(size, 1), 100);
     * 
     * boolean hasSearch = hasSearch(search);
     * 
     * String whereClause = hasSearch ? " WHERE LOWER(p.name) LIKE LOWER(:search)" :
     * "";
     * String orderBy = " ORDER BY p.id";
     * 
     * // 🔹 Data query
     * var query = em.createQuery(
     * "FROM ProductEntity p" + whereClause + orderBy,
     * ProductEntity.class);
     * 
     * setSearchParameter(query, search, hasSearch);
     * 
     * List<ProductEntity> entities = query
     * .setFirstResult(page * size)
     * .setMaxResults(size)
     * .getResultList();
     * 
     * // 🔹 Count query
     * var countQuery = em.createQuery(
     * "SELECT COUNT(p) FROM ProductEntity p" + whereClause,
     * Long.class);
     * 
     * setSearchParameter(countQuery, search, hasSearch);
     * 
     * long total = countQuery.getSingleResult();
     * 
     * List<Product> products = entities.stream()
     * .map(InventoryEntityMapper::toDomain)
     * .toList();
     * 
     * return new PageResult<>(products, total, page, size);
     * }
     * 
     * private boolean hasSearch(String search) {
     * return search != null && !search.isBlank();
     * }
     * 
     * private void setSearchParameter(jakarta.persistence.Query query, String
     * search, boolean hasSearch) {
     * if (hasSearch) {
     * query.setParameter("search", "%" + search + "%");
     * }
     * }
     */

}
