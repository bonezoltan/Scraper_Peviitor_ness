package DTO;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class JobInformation {

    private String id;
    private String job_title;
    private String job_link;
    private String company;
    private String country;
    private String remote;
    private String city;

}
