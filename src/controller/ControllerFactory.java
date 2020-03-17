package controller;

import domain.Person;
import domain.PersonService;

public class ControllerFactory {
	
    public SyncHandler getSyncController(String key, PersonService model) {
        return createSyncHandler(key, model);
    }

    public AsyncHandler getAsyncController(String key, PersonService model){ return createAsyncHandler(key, model); }

    private AsyncHandler createAsyncHandler(String handlerName,PersonService model){
    	AsyncHandler handler = null;
    	try{
    		Class<?> handlerClass = Class.forName("controller."+ handlerName);
    		Object handlerObject = handlerClass.newInstance();
    		handler = (AsyncHandler) handlerObject;
    		handler.setModel(model);
		}catch (Exception e){
    		throw new RuntimeException("Deze pagina bestaat niet!!!!!");
		}
    	return handler;
	}

	private SyncHandler createSyncHandler(String handlerName, PersonService model) {
		SyncHandler handler = null;
		try {
			Class<?> handlerClass = Class.forName("controller."+ handlerName);
			Object handlerObject = handlerClass.newInstance();
			handler = (SyncHandler) handlerObject;
	    	handler.setModel(model);
		} catch (Exception e) {
			throw new RuntimeException("Deze pagina bestaat niet!!!!");
		}
		return handler;
	}


}
