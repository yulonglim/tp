package seedu.address.ui;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.Class.Class;

import java.util.logging.Logger;

/**
 * Panel containing the list of persons.
 */
public class ClassListPanel extends UiPart<Region> {
    private static final String FXML = "ClassListPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(ClassListPanel.class);

    @FXML
    private ListView<Class> classListView;

    /**
     * Creates a {@code PersonListPanel} with the given {@code ObservableList}.
     */
    public ClassListPanel(ObservableList<Class> classList) {
        super(FXML);
        classListView.setItems(classList);
        classListView.setCellFactory(listView -> new ClassListViewCell());
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Person} using a {@code PersonCard}.
     */
    class ClassListViewCell extends ListCell<Class> {
        @Override
        protected void updateItem(Class classObj, boolean empty) {
            super.updateItem(classObj, empty);

            if (empty || classObj == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new ClassCard(classObj, getIndex() + 1).getRoot());
            }
        }
    }

}
