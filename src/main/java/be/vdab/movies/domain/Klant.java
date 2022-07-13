package be.vdab.movies.domain;

public record Klant(long id, String naam, String straatEnHuisnummer, String postcode
        , String gemeente) {
}
