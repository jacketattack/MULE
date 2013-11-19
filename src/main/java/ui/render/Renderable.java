package ui.render;

/**
 * Renderable guarantees that an object can return a Render object
 */
public interface Renderable 
{
	/**
	 * Get the render for this object
	 * @return The render
	 */
	public Render getRender();
}
