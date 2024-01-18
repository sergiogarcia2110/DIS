package org.vaadin.example;

import com.vaadin.flow.router.Route;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

import java.io.IOException;
import java.util.List;

@Route
public class MainView extends VerticalLayout {

    private ComboBox<String> comboBox;
    private Grid<ObservationWithoutMSCode> observationGrid;
    private final ObservationService observationService = new ObservationService();

    public MainView() {
        // Configure ComboBox
        comboBox = new ComboBox<>("Select MsCode");
        comboBox.setLabel("MsCode");

        // Add ComboBox to the view
        add(comboBox);

        observationGrid = new Grid<>(ObservationWithoutMSCode.class);
        add(observationGrid);

        // Load MsCodes in the ComboBox
        loadMsCodes();

        // Update Grid
        comboBox.addValueChangeListener(event -> updateGridData(event.getValue()));
    }

    private void loadMsCodes() {
        try {
            List<String> msCodes = observationService.fetchMsCodes();
            comboBox.setItems(msCodes);
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void updateGridData(String msCode) {
        try {
            List<ObservationWithoutMSCode> observations = observationService.fetchObservationsByMsCode(msCode);
            observationGrid.setItems(observations);
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
