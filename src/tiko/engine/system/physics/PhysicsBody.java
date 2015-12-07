package tiko.engine.system.physics;

/**
 * Object used for physic calculations.
 *
 * Object can be used for physics calculations. It contains data for physics
 * like mass, drag and bounciness. It also contains collider for checking
 * collision with other collider. PhysicsBody can have layer for enhanced
 * collision detection.
 *
 * @author Jani Timonen
 * @version 1.0
 * @since 1.8
 */
public class PhysicsBody {

    /**
     * Collider used to check collision with other objects.
     */
    private Collider collider;

    /**
     * Mass used for physics calculations.
     */
    private int mass;

    /**
     * Drag used for physics calculations.
     */
    private int drag;

    /**
     * Bounciness used for physics calculations.
     */
    private int bounciness;

    /**
     * Boolean to represent does different forces apply to this PhysicsBody.
     */
    private boolean kinetic;

    /**
     * Layer of this PhysicsBody. Used for enhanced collision detection (NYI)
     */
    private String layer;

    /**
     * Default constructor.
     *
     * Constructor to set all different values for PhysicsBody
     *
     * @param collider Collider used for collision detection.
     * @param mass Mass of the object.
     * @param drag Drag of the object.
     * @param bounciness Bounciness of the object.
     * @param kinetic Is this object kinetic?
     */
    public PhysicsBody(Collider collider,
                       int mass,
                       int drag,
                       int bounciness,
                       boolean kinetic) {

        this.mass = mass;
        this.drag = drag;
        this. bounciness = bounciness;
        this.kinetic = kinetic;
        this.collider = collider;
        layer = "default";
    }

    /**
     * Checks collision with other collider.
     *
     * @param other other Collider.
     * @return true - these two colliders do collide, false - these two
     * colliders do not collide.
     */
    public boolean checkCollision(Collider other) {

        boolean result = false;

        if (collider.getCollider().getBounds().intersects(
                other.getCollider().getBounds()
        )) {
            result = true;
        }

        return result;
    }

    /**
     * Returns collider of PhysicsBody.
     *
     * @return Collider of PhysicsBody
     */
    public Collider getCollider() {
        return collider;
    }

    /**
     * Returns sets collider of PhysicsBody.
     *
     * @param collider Collider of PhysicsBody
     */
    public void setCollider(Collider collider) {
        this.collider = collider;
    }

    /**
     * Gets layer of this PhysicsBody.
     *
     * @return layer name as String.
     */
    public String getLayer() {
        return layer;
    }

    /**
     * Sets layer of this PhysicsBody.
     *
     * @param layer layer of this PhysicsBody
     */
    public void setLayer(String layer) {
        this.layer = layer;
    }

    /**
     * Returns is this PhysicsBody is kinetic.
     *
     * @return true - kinetic, false - not kinetic.
     */
    public boolean isKinetic() {
        return kinetic;
    }

    /**
     * Sets if this PhysicsBody is kinetic.
     *
     * @param kinetic true - kinetic, false - not kinetic.
     */
    public void setKinetic(boolean kinetic) {
        this.kinetic = kinetic;
    }

    /**
     * Returns bounciness of this PhysicsBody.
     *
     * @return bounciness of this PhysicsBody.
     */
    public int getBounciness() {
        return bounciness;
    }

    /**
     * Sets bounciness of this PhysicsBody.
     *
     * @param bounciness bounciness of PhysicsBody.
     */
    public void setBounciness(int bounciness) {
        this.bounciness = bounciness;
    }

    /**
     * Returns drag of this PhysicsBody.
     *
     * @return drag of this PhysicsBody.
     */
    public int getDrag() {
        return drag;
    }

    /**
     * Sets drag of this PhysicsBody.
     *
     * @param drag drag of this PhysicsBody.
     */
    public void setDrag(int drag) {
        this.drag = drag;
    }

    /**
     * Returns mass of this PhysicsBody.
     *
     * @return  mass of this PhysicsBody.
     */
    public int getMass() {
        return mass;
    }

    /**
     * Sets mass of this PhysicsBody.
     *
     * @param mass mass of this PhysicsBody.
     */
    public void setMass(int mass) {
        this.mass = mass;
    }
}
