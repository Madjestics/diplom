package diplom.data.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class DataArray {

    List<List<Double>> SfriArrays;
    List<List<Double>> PresArrays;
}
