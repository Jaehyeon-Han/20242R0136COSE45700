Goal: Create a vector graphic editor with design patterns applied.

# Functional Requirements
1. Objects
   - Types: Lines(straight, free), shapes(rectangle, triangle, ellipse, etc), image, text
   - Properties: Width/height, position, z-order, color
   - Can be multiselected with Ctrl + Left-click.
   - Can be resized by setting values in the property window? (or with mouse-drag if there's a frame).
2. Property Window
   - Shows the properties of selected object. (none if not selected, what about multi-selected case?)
   - Properties on window should be synchronized with the objects.
3. Design Patterns
  - Abstract Factory: To get an object in a specific type
  - Singleton
  - ★ Composite: Multi-selected objects are considered same as one object.
  - State
  - Command
  - Observer: To sync the property window and objects maybe?
  - Decorator
4. Additional Features
  -  Show a frame that wraps the object.
  -  Zoom in or zoom out with Ctrl + Wheel, Ctrl + '+', or a button.
  -  Show texts inside objects?
  -  Show shadows of objects.

# Current Plans
1. Create a minimum viable product with a simple class design. (10.13)
   1. Research how vector graphics are different from raster graphics and if thereare vector graphics libraries in Java that can be used.
   2. Narrow down the requirements to build an MVP.

