package be.vdab.movies.domain;

import org.springframework.format.annotation.NumberFormat;

import javax.validation.constraints.PositiveOrZero;
import java.math.BigDecimal;

public class Film{

    private final long id;
    private final long genreId;
    private final String titel;
    @PositiveOrZero @NumberFormat(pattern = "#0")
    private BigDecimal voorraad;
    @PositiveOrZero @NumberFormat(pattern = "#0")
    private final BigDecimal gereserveerd;
    @PositiveOrZero @NumberFormat(style = NumberFormat.Style.CURRENCY)
    private final BigDecimal prijs;

    public Film(long id, long genreId, String titel, BigDecimal voorraad, BigDecimal gereserveerd, BigDecimal prijs) {
        this.id = id;
        this.genreId = genreId;
        this.titel = titel;
        this.voorraad = voorraad;
        this.gereserveerd = gereserveerd;
        this.prijs = prijs;
    }

    public long getId() {
        return id;
    }

    public long getGenreId() {
        return genreId;
    }

    public String getTitel() {
        return titel;
    }

    public BigDecimal getVoorraad() {
        return voorraad;
    }

    public BigDecimal getGereserveerd() {
        return gereserveerd;
    }

    public BigDecimal getPrijs() {
        return prijs;
    }

    public BigDecimal aantalBeschikbaar(BigDecimal gereserveerd) {
        if (gereserveerd.compareTo(BigDecimal.ZERO) <= 0 || gereserveerd.compareTo(voorraad) > 0) {
           return voorraad;
        }
        return voorraad.subtract(gereserveerd);
    }
    
}
