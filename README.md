# Description
An implementation of a grid layout in Vaadin. For every developer who wants the grid layout back!

# Features 

- Create a grid layout, which displays the added components.
- Configure the amount of rows and columns.
- Add components to the grid on specified cells.
- Get components of the grid of specified cells.
- Create tooltips for each grid cell separately.
- Display a border if needed.
- Add striped rows or columns to the grid, starting with the first or the second row/column.
- Set the width/height of the grid to hard coded values if you need.

- The grid automatically uses the needed size of its components, if you don't specify its width/height.
- The grid will show a scrollbar if its width is greater than 100%.
- The grid will not show empty rows and columns.

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
    grid.setStriped("#EAB251", true, true);
    grid.setStriped("rgba(234, 178, 81, 0.3)", false, false);

#### Step 3: Profit
![vaadin-dw-grid-layout-with-border](https://github.com/davidweber411/VaadinDwGridLayout/assets/108978258/bef7adb7-82c4-4530-8316-113f557a9d46)

![vaadin-dw-grid-layout-without-border](https://github.com/davidweber411/VaadinDwGridLayout/assets/108978258/015d2d6d-8d50-4796-9ac7-c393517f7e12)

![image](https://github.com/davidweber411/VaadinDwGridLayout/assets/108978258/98751252-4581-48c1-8fab-c60a11da3791)

![image](https://github.com/davidweber411/VaadinDwGridLayout/assets/108978258/3d84bb57-fe64-4ae1-b56d-82dac34f76cd)

![image](https://github.com/davidweber411/VaadinDwGridLayout/assets/108978258/cf91dcc6-a8de-48e4-bcbb-e3429670ebad)

![image](https://github.com/davidweber411/VaadinDwGridLayout/assets/108978258/0ced52db-787a-4457-8e06-81af2fa56e0c)

