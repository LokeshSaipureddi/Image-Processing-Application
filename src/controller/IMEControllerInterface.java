package controller;

import java.io.IOException;

import model.IMGModel;

/**
 * Interface for Controller, to Control the flow of program execution.
 */
public interface IMEControllerInterface {

  /**
   * Hand control to the controller.
   *
   * @param model model instance
   * @throws IOException  if IO cannot execute
   */
  void start(IMGModel model) throws IOException;
}