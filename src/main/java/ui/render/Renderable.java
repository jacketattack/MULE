package ui.render;

/**
 * Renderable gaurenties that an object can return a Render object
 */
public interface Renderable 
{
	/**
	 * Get the render for this object
	 * @return The render
	 */
	public Render getRender();
}
