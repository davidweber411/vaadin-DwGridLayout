# Description
An implementation of a grid layout in Vaadin. For every developer who wants the grid layout back!

# How to use 

#### Step 1: Import the class
Just copy the class "DwGridLayout" into your Vaadin project.

#### Step 2: Usage example
Use it like this:

    DwGridLayout grid = new DwGridLayout(5, 9);
    grid.setId("MyDwGridLayoutId");
    grid.setDisplayBorder(true);
    grid.addComponent(0, 0, createButtonWithSizeInPx(75, 75));
    grid.addComponent(2, 6, createButtonWithSizeInPx(500, 100));
    grid.addTooltipForComponent(2, 6, "Should not be displayed in 2,6");
    grid.addComponent(2, 6, createButtonWithSizeInPx(500, 100));
    grid.addComponent(2, 6, createButtonWithSizeInPx(500, 100));
    grid.addComponent(4, 7, createButtonWithSizeInPx(300, 300));
    grid.addComponent(3, 2, createButtonWithSizeInPx(100, 200));
    grid.addTooltipForComponent(4, 7, "CRAAAAAAAAAAAAAAAZYYYYYY 4,7");

#### Step 3: Profit
