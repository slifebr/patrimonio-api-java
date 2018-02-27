package br.com.slifesys.patrimonio.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.slifesys.patrimonio.model.Item;

public interface ItemRepository extends JpaRepository<Item, Long> {

	
}
