
package components;

import java.util.EventObject;

public class CollisionEvent extends EventObject {
    
    private Object collisionObject;

    public CollisionEvent(Object source, Object collisionObject) {
        super(source);
        this.collisionObject = collisionObject;
    }

    public Object getCollisionObject() {
        return collisionObject;
    }
    
    
}
