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
