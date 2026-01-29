package uce.edu.web.api.matricula.aplication.representation;

public class LinkDTO {

    public String href;
    public String rel;

    public LinkDTO(String href, String rel) {
        this.href = href;
        this.rel = rel;
    }

    public LinkDTO() {
    }

}
