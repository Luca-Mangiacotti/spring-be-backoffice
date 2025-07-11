package org.lessons.java.spring_be_backoffice.model;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "comics")
public class Comic {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    @NotBlank(message = "Il titolo non può essere blank, né empty né null")
    @Column(name = "title")
    private String title;

    @NotNull
    @NotBlank(message = "L'autore non può essere blank, né empty né null")
    @Column(name = "author")
    private String author;

    @Lob
    @Column(name = "description", nullable = true)
    private String description;

    @NotNull(message = "Il prezzo non può essere negativo")
    @Min(value = 1)
    @Column(name = "price")
    private BigDecimal price;

    @NotNull(message = "Lo stock non può essere negativo")
    @Min(value = 1)
    @Column(name = "stock_quantity")
    private int stockQuantity;

    @Column(name = "release_date")
    private LocalDate releaseDate;

    @NotBlank(message = "L'url non può essere vuoto")
    @NotNull
    @Column(name = "image_url")
    private String imageUrl;

    @ManyToOne(fetch = FetchType.EAGER)

    @JoinColumn(name = "category_id")
    private Category category;

    // Getter e Setter

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public int getStockQuantity() {
        return stockQuantity;
    }

    public void setStockQuantity(int stockQuantity) {
        this.stockQuantity = stockQuantity;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return String.format(
                "Titolo: %s, Autore: %s, Quantità: %s, Data di rilascio: %s, Descrizione: %s, Prezzo: %s €",
                this.title, this.author, this.stockQuantity, this.releaseDate, this.description, this.price);
    }
}