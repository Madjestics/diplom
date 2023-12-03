package diplom.web.controller;

import diplom.buisness.VisualizationService;
import diplom.data.dto.DataArray;
import diplom.data.dto.IndexesData;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name="Visualization Controller", description = "Возвращает массивы для отображения")
@RestController
@RequestMapping("/api/visualize")
@RequiredArgsConstructor
public class VisualizationController {

    @Autowired
    private final VisualizationService visualizationService;

    @Operation(description = "Возвращает массивы линий для визуализации")
    @PostMapping("/lines")
    public List<DataArray> getArraysForVisualizeLine(@RequestBody IndexesData indexesData) {
        return visualizationService.getArraysForVisualizeLines(indexesData);
    }
}
