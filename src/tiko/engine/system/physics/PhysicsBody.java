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
    private float mass;

    /**
     * Drag used for physics calculations.
     */
    private float drag;

    /**
     * Bounciness used for physics calculations.
     */
    private float bounciness;

    /**
     * Boolean to represent does different forces apply to this PhysicsBody.
     */
    private boolean kinetic;

    /**
     * Layer of this PhysicsBody. Used for enhanced collision detection (NYI)
     */
    private PhysicsLayer layer;

    /**
     * Vertical force of body.
     */
    private float verticalForce;

    /**
     * Horizontal force of body.
     */
    private float horizontalForce;

    /**
     * Maximum vertical force of body. Zero means unlimited force.
     */
    private float maxVerticalForce;

    /**
     * Maximum horizontal force of body. Zero means unlimited force.
     */
    private float maxHorizontalForce;

    /**
     * Indicates if this body is not touching ground layer.
     */
    private boolean inAir;

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
                       float mass,
                       float drag,
                       float bounciness,
                       boolean kinetic) {

        this.mass = mass;
        this.drag = drag;
        this. bounciness = bounciness;
        this.kinetic = kinetic;
        this.collider = collider;
        layer = new PhysicsLayer("default");

        horizontalForce = 0;
        verticalForce = 0;
        maxHorizontalForce = 0;
        maxVerticalForce = 0;
        inAir = true;
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
     * Adds force to this body.
     *
     * @param amount amount of force for this body.
     * @param direction true - force for horizontal, false - force for vertical.
     */
    public void addForce(float amount, boolean direction) {

        if (!direction) {
            horizontalForce += amount;
        } else {
            verticalForce += amount;
        }
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
    public PhysicsLayer getLayer() {
        return layer;
    }

    /**
     * Sets layer of this PhysicsBody.
     *
     * @param layer layer of this PhysicsBody
     */
    public void setLayer(PhysicsLayer layer) {
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
    public float getBounciness() {
        return bounciness;
    }

    /**
     * Sets bounciness of this PhysicsBody.
     *
     * @param bounciness bounciness of PhysicsBody.
     */
    public void setBounciness(float bounciness) {
        this.bounciness = bounciness;
    }

    /**
     * Returns drag of this PhysicsBody.
     *
     * @return drag of this PhysicsBody.
     */
    public float getDrag() {
        return drag;
    }

    /**
     * Sets drag of this PhysicsBody.
     *
     * @param drag drag of this PhysicsBody.
     */
    public void setDrag(float drag) {
        this.drag = drag;
    }

    /**
     * Returns mass of this PhysicsBody.
     *
     * @return  mass of this PhysicsBody.
     */
    public float getMass() {
        return mass;
    }

    /**
     * Sets mass of this PhysicsBody.
     *
     * @param mass mass of this PhysicsBody.
     */
    public void setMass(float mass) {
        this.mass = mass;
    }

    /**
     * Returns horizontal force.
     *
     * @return horizontal force.
     */
    public float getForceH() {
        return horizontalForce;
    }

    /**
     * Returns vertical force.
     *
     * @return vertical force.
     */
    public float getForceV() {
        return verticalForce;
    }

    /**
     * Sets vertical force. Also checks that force won't be over maximum force.
     *
     * @param force new vertical force.
     */
    public void setVerticalForce(float force) {

        // Checks if force does not have limit.
        if (maxVerticalForce == 0f) {
            verticalForce = force;
        } else {

            // Too high positive force will be set to max positive force.
            if (force > maxVerticalForce) {
                verticalForce = maxVerticalForce;
            } else if (force < -maxVerticalForce) {
                // Too high negative force will be set to max negative force.
                verticalForce = -maxVerticalForce;
            } else {
                verticalForce = force;
            }
        }
    }

    /**
     * Sets horizontal force.
     *
     * Also checks that force won't be over maximum force.
     *
     * @param force new horizontal force.
     */
    public void setHorizontalForce(float force) {

        // Checks if force does not have limit.
        if (maxHorizontalForce == 0f) {
            horizontalForce = force;
        } else {

            // Too high positive force will be set to max positive force.
            if (force > maxHorizontalForce) {
                horizontalForce = maxHorizontalForce;
            } else if (force < -maxHorizontalForce) {
                // Too high negative force will be set to max negative force.
                horizontalForce = -maxHorizontalForce;
            } else {
                horizontalForce = force;
            }
        }
    }

    /**
     * Returns if this object is in the air.
     *
     * @return true - in air, false - not in air.
     */
    public boolean isInAir() {
        return inAir;
    }

    /**
     * Sets if this object is in the air.
     *
     * @param inAir true - in air, false - not in air.
     */
    public void setInAir(boolean inAir) {
        this.inAir = inAir;
    }

    /**
     * Returns maximum vertical force.
     *
     * @return maximum vertical force.
     */
    public float getMaxVerticalForce() {
        return maxVerticalForce;
    }

    /**
     * Sets maximum vertical force.
     *
     * @param maxVerticalForce maximum vertical force.
     */
    public void setMaxVerticalForce(float maxVerticalForce) {
        this.maxVerticalForce = maxVerticalForce;
    }

    /**
     * Returns maximum horizontal force.
     *
     * @return maximum horizontal force.
     */
    public float getMaxHorizontalForce() {
        return maxHorizontalForce;
    }

    /**
     * Sets maximum horizontal force.
     *
     * @param maxHorizontalForce maximum horizontal force.
     */
    public void setMaxHorizontalForce(float maxHorizontalForce) {
        this.maxHorizontalForce = maxHorizontalForce;
    }
}
