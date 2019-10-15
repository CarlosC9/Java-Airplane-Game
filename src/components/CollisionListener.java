
package components;

import java.util.EventListener;


public interface CollisionListener extends EventListener {
    
    public void onCollision(CollisionEvent evt);
    
}
