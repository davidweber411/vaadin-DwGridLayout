package org.vaadin.example.components;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Label;

import java.util.ArrayList;
import java.util.List;

public class DwGridLayout extends Composite<Div> {

    private interface MatrixConsumer {
        void execute(int colIndex, int rowIndex);
    }

    private final static String CSS_CLASS_CELL_POSITION_COL_INDEX_PREFIX = "cell-pos-col-";
    private final static String CSS_CLASS_CELL_POSITION_ROW_INDEX_PREFIX = "cell-pos-row-";
    private final static String CSS_CLASS_CELL_CONTENT_DEFAULT = "cell-content-default";
    private final static String CSS_BORDER_STYLE = "1px solid grey";

    private final int amountOfColumns;
    private final int amountOfRows;
    private boolean displayBorder;
    private Div[][] colRowCellMatrix;

    public DwGridLayout(int amountOfColumns, int amountOfRows) {
        this.amountOfColumns = amountOfColumns;
        this.amountOfRows = amountOfRows;
        this.colRowCellMatrix = new Div[amountOfColumns][amountOfRows];
        this.getContent().getStyle().set("display", "grid");
        this.getContent().getStyle().set("grid-template-columns", "min-content ".repeat(this.amountOfColumns));
        this.getContent().getStyle().set("overflow-x", "auto");
        this.getContent().getStyle().set("width", "100%");

        initCellMatrix();
        reloadGridComposite();
    }

    private void initCellMatrix() {
        iterateOverMatrixAndExecute((colIndex, rowIndex) -> colRowCellMatrix[colIndex][rowIndex] = createCell(colIndex, rowIndex, null));
    }

    private void reloadGridComposite() {
        this.getContent().removeAll();
        iterateOverMatrixAndExecute((colIndex, rowIndex) -> this.getContent().add(colRowCellMatrix[colIndex][rowIndex]));

        if (displayBorder) {
            displayBorder();
        }
    }

    private void displayBorder() {
        for (int rowIndex = 0; rowIndex < amountOfRows; rowIndex++) {
            colRowCellMatrix[0][rowIndex].getStyle().set("border-left", CSS_BORDER_STYLE);
            boolean atLeastOneWrapperHasContent = getComponentWrappersOfRow(rowIndex).stream()
                    .anyMatch(compWrapper -> !compWrapper.getClassNames().contains(CSS_CLASS_CELL_CONTENT_DEFAULT));
            if (atLeastOneWrapperHasContent) {
                for (int columnIndex = 0; columnIndex < amountOfColumns; columnIndex++) {
                    colRowCellMatrix[columnIndex][rowIndex].getStyle().set("border-bottom", CSS_BORDER_STYLE);
                }
            }
        }
        for (int columnIndex = 0; columnIndex < amountOfColumns; columnIndex++) {
            colRowCellMatrix[columnIndex][0].getStyle().set("border-top", CSS_BORDER_STYLE);
            boolean atLeastOneWrapperHasContent = getComponentWrappersOfColumn(columnIndex).stream()
                    .anyMatch(compWrapper -> !compWrapper.getClassNames().contains(CSS_CLASS_CELL_CONTENT_DEFAULT));
            if (atLeastOneWrapperHasContent) {
                for (int rowIndex = 0; rowIndex < amountOfRows; rowIndex++) {
                    colRowCellMatrix[columnIndex][rowIndex].getStyle().set("border-right", CSS_BORDER_STYLE);
                }
            }
        }
    }

    private List<Div> getComponentWrappersOfRow(int rowIndex) {
        List<Div> components = new ArrayList<>();
        for (int i = 0; i < amountOfColumns; i++) {
            components.add(colRowCellMatrix[i][rowIndex]);
        }
        return components;
    }

    private List<Div> getComponentWrappersOfColumn(int columnIndex) {
        List<Div> components = new ArrayList<>();
        //noinspection ManualArrayToCollectionCopy
        for (int i = 0; i < amountOfRows; i++) {
            components.add(colRowCellMatrix[columnIndex][i]);
        }
        return components;
    }

    private void iterateOverMatrixAndExecute(MatrixConsumer matrixConsumer) {
        for (int rowIndex = 0; rowIndex < amountOfRows; rowIndex++) {
            for (int colIndex = 0; colIndex < amountOfColumns; colIndex++) {
                matrixConsumer.execute(colIndex, rowIndex);
            }
        }
    }

    private Div createCell(int columnIndex, int rowIndex, Component componentToInput) {
        Div div = new Div();
        div.getClassNames().add(CSS_CLASS_CELL_POSITION_ROW_INDEX_PREFIX + rowIndex);
        div.getClassNames().add(CSS_CLASS_CELL_POSITION_COL_INDEX_PREFIX + columnIndex);
        if (componentToInput == null) {
            div.getClassNames().add(CSS_CLASS_CELL_CONTENT_DEFAULT);
        } else {
            div.getClassNames().remove(CSS_CLASS_CELL_CONTENT_DEFAULT);
        }
        div.add(componentToInput == null ? new Div() : componentToInput);
        return div;
    }

    private void validateCoordinates(int columnIndex, int rowIndex) {
        if (columnIndex >= amountOfColumns) {
            throw new IllegalArgumentException("ColumnIndex " + columnIndex + " is out of bounds of the grid. The biggest ColumnIndex in grid is: " + amountOfColumns);
        }
        if (rowIndex >= amountOfRows) {
            throw new IllegalArgumentException("RowIndex " + rowIndex + " is out of bounds of the grid. The biggest RowIndex in grid is: " + amountOfRows);
        }
    }

    public Component getComponent(int columnIndex, int rowIndex) {
        validateCoordinates(columnIndex, rowIndex);
        Div componentWrapper = colRowCellMatrix[columnIndex][rowIndex];
        return componentWrapper.getChildren().findFirst().orElseThrow();
    }

    public void addComponent(int columnIndex, int rowIndex, Component newComponent) {
        validateCoordinates(columnIndex, rowIndex);

        Div[][] matrixWithNewComponent = new Div[amountOfColumns][amountOfRows];
        iterateOverMatrixAndExecute((colIndexE, rowIndexE) -> matrixWithNewComponent[colIndexE][rowIndexE] =
                (colIndexE == columnIndex && rowIndexE == rowIndex)
                        ? createCell(colIndexE, rowIndexE, newComponent)
                        : colRowCellMatrix[colIndexE][rowIndexE]);
        colRowCellMatrix = matrixWithNewComponent;
        reloadGridComposite();
    }

    public void setId(String id) {
        this.getContent().setId(id);
    }

    public void setDisplayBorder(boolean displayBorder) {
        this.displayBorder = displayBorder;
    }

    /**
     * <b style="color: red;">Beware: Adding a new Component to a position with a tooltip will remove the tooltip.</b>
     */
    public void addTooltipForComponent(int columnIndex, int rowIndex, String tooltip) {
        colRowCellMatrix[columnIndex][rowIndex].setTitle(tooltip);
        reloadGridComposite();
    }

    @SuppressWarnings("unused")
    private Label createDebugCell(int colIndex, int rowIndex) {
        return new Label(colIndex + ":" + rowIndex);
    }

}
