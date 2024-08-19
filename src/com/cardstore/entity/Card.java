package com.cardstore.entity;

import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;

@Entity
@Table(name="card")
@NamedQueries({
	@NamedQuery(name = "Card.findAll", query = "SELECT c from Card c ORDER BY c.serialNumber"),	
	@NamedQuery(name = "Card.countAll", query = "SELECT Count(*) FROM Card c")	
})

public class Card implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private String cardName;
	private String game;
	private String serialNumber;
	private String description;
	private double marketprice;
	private String imageUrl;

	public Card() {
		
	}
	
	public Card(String cardName, String game, String serialNumber, String description, double marketprice,
			String imageUrl) {
		super();
		this.cardName = cardName;
		this.game = game;
		this.serialNumber = serialNumber;
		this.description = description;
		this.marketprice = marketprice;
		this.imageUrl = imageUrl;
	}

	@Column(name="cardName", nullable=false, length=200)
	public String getCardName() {
		return cardName;
	}

	public void setCardName(String cardName) {
		this.cardName = cardName;
	}

	@Column(name="game", nullable=false, length=50)
	public String getGame() {
		return game;
	}

	public void setGame(String game) {
		this.game = game;
	}

	@Id
	@Column(name="serialNumber", unique=true, nullable=false, length=50)
	public String getSerialNumber() {
		return serialNumber;
	}

	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}

	@Column(name="description", nullable=false, columnDefinition = "TEXT")
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Column(name="marketprice", nullable=false)
	public double getMarketprice() {
		return marketprice;
	}

	public void setMarketprice(double marketprice) {
		this.marketprice = marketprice;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	@Override
	public String toString() {
		return "Card [cardName=" + cardName + ", game=" + game + ", serialNumber=" + serialNumber + ", description="
				+ description + ", marketprice=" + marketprice + ", imageUrl=" + imageUrl + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(cardName, description, game, imageUrl, marketprice, serialNumber);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Card other = (Card) obj;
		return Objects.equals(cardName, other.cardName) && Objects.equals(description, other.description)
				&& Objects.equals(game, other.game) && Objects.equals(imageUrl, other.imageUrl)
				&& Double.doubleToLongBits(marketprice) == Double.doubleToLongBits(other.marketprice)
				&& Objects.equals(serialNumber, other.serialNumber);
	}

}
