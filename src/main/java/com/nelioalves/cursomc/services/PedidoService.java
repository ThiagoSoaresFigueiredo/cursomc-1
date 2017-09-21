package com.nelioalves.cursomc.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nelioalves.cursomc.domain.ItemPedido;
import com.nelioalves.cursomc.domain.Pedido;
import com.nelioalves.cursomc.repositories.ItemPedidoRepository;
import com.nelioalves.cursomc.repositories.PedidoRepository;
import com.nelioalves.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class PedidoService {
	
	@Autowired
	private PedidoRepository repo;
	
	@Autowired 
	private ItemPedidoRepository itemPedidoReposiroty;
	
	public Pedido buscar(Integer id) {
		Pedido obj = repo.findOne(id);
		if (obj == null) {
			throw new ObjectNotFoundException("Objeto não encontrado! Id: " + id
					+ ", Tipo: " + Pedido.class.getName());
		}
		return obj;
	}

	public void inserir(Pedido obj) {
		obj.setId(null);
		repo.save(obj);
		for (ItemPedido ip : obj.getItens()) {
			ip.setPedido(obj);
		}
		itemPedidoReposiroty.save(obj.getItens());
	}
	
}
