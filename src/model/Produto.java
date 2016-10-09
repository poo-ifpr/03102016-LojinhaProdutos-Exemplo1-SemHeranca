package model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import util.DateUtils;

@Entity
public class Produto {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	
	@Column(nullable=false, length=80)
	private String nome;
	
	@Column(nullable=false)
	private long preco;
	
	@Temporal(TemporalType.DATE)
	private Date dataValidade;
	
	@Column(nullable=false)
	private boolean importado;
	
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public long getPreco() {
		return preco;
	}
	public void setPreco(long preco) {
		this.preco = preco;
	}
	public Date getDataValidade() {
		return dataValidade;
	}
	public void setDataValidade(Date dataValidade) {
		this.dataValidade = dataValidade;
	}
	public boolean isImportado() {
		return importado;
	}
	public void setImportado(boolean importado) {
		this.importado = importado;
	}
	
	public double getPrecoFinal(){
		double preco = getPreco();
		Date hoje = DateUtils.hoje();
		if(dataValidade != null){
			Date semana_que_vem = DateUtils.
					somar(hoje, 7);
			if(semana_que_vem.after(dataValidade)){
				preco = preco * 0.9;
			}
		}
		if(isImportado()){
			preco = preco  * 1.2;
		}
		return Math.round(preco * 100.0) / 100.0;
	}
	
	public Produto() {
	}
	
	@Override
	public String toString() {
		return "Produto [id=" + id + ", nome=" + nome + ", preco=" + preco + ", dataValidade=" + dataValidade
				+ ", importado=" + importado + "]";
	}
	public Produto(String nome, long preco, Date dataValidade, boolean importado) {
		this.nome = nome;
		this.preco = preco;
		this.dataValidade = dataValidade;
		this.importado = importado;
	}
	
	
	
	
}
