The Implementation uses Model View Controller design pattern, with Command design pattern to tackle commands.

DESIGN

Model classes:
• Model consists of a single Image with all the operations inside the class, i.e. transforms and processing of an image.
	- Iterface IMEImage, implemented by IMEImageImpl, Mocked by ModelMock
• To be able to easily replace with a mock or to replace with a new IMEImage implementation we have Generator classes to create a IMEImage instance. Can be passed to the controller easily to get the job done.
	- Interface IMGModel, implemented by GenModel, Mocked by MockGen


Controller classes:
• Controller consists of a hash map to hold String keys and corresponding IMEImage instance (our model).
• Controller will call the respective commands on the stored Model instances, and orchestrate the sequence of execution.
• Follows command design pattern to swiftly add commands. The commands are in commands package.
	- Interface controller.IMEControllerInterface, implemented by controller.TstController, Main in controller.IMEController

We were asked not to implement view.

HOW TO RUN

paste the following line after running the program
run src/script.txt


Example Image source: https://www.pinterest.se/pin/233694668154310792/ resized to save memory

++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

Version Assigment 5

(We already had a good design which didn't require us to modify more than 1-2 methods, that too splitting and not altering the sequence of command execution).

UPDATES:
• Added functionality to draw on a canvas (empty) image.
	- added classes: interface DrawImage, implementation class DrawImageImpl
• Added functionality to IO with a BufferedImage instance.
	- split readOthers function in ImageIO into readOther and readBufferedImage (not modifying any line) 
	- added method in Factory model generator to generate model instance from a BufferedImage instance.
• Added methods to plot histogram, compress, color correct and level adjust in IMEImageImpl class and it's IMEImage interface

• Split convolution into a helper function and main function to improve readablity(had a feedback of being long).
• Added command classes for new functionality of histogram, compress, color correct and level adjust
• Added case for preview in command existing command classes of blur, sepia, valueComponent etc.
• Added a command registry class to encapsulate the command map for the controller class
• Refractored controller class to the controller package.

++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

Version Assignment 6

(No changes in the design)

HOW TO RUN:
	GUI: java -jar Ass4_1.jar
	CLI: java -jar Ass4_1.jar -text
	Script: java -jar Ass4_1.jar -file path-to-script

UPDATES:
• Added Package gor views
	- added classes: interface View, implementation class MockView(To help test), implementation class SwingUIFrame
• Added Features interface and classes to enable GUI view
	- added classes: interface Features, implementation FeaturesImpl
• Added support for "-text" for cli and "-file path-to-script" for cli and script running respectively
• Removed save functionality from the model and keeping it only in the controller
• Added SplitView command to optimize running time in GUI preview images
	- added class SplitView
	- added in command registry 