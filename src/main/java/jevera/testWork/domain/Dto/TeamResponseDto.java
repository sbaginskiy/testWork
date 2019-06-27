package jevera.testWork.domain.Dto;

public class TeamResponseDto {
    private Long id;
    private String name;
    private ETPDto etpDto;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
