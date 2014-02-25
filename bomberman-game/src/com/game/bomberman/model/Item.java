package com.game.bomberman.model;

// -------------------------------------------------------------------------
/**
 * The item class for the game
 *
 * @author Ching Hsiang Chen (chingyg)
 * @author Kyle He (kyleh8)
 * @author Danh Nguyen (danh0902)
 * @version Dec 8, 2013
 */
public class Item
{
    private float reward;


    // ----------------------------------------------------------
    /**
     * Create a new Item object.
     *
     * @param reward
     *            the value of the reward
     */
    public Item(float reward)
    {
        this.reward = reward;
    }


    // ----------------------------------------------------------
    /**
     * Gets the reward
     *
     * @return the value of the reward
     */
    public float getReward()
    {
        return reward;
    }


    /**
     * Sets the reward to the given value
     *
     * @param reward
     *            the value given
     */
    public void setReward(int reward)
    {
        this.reward = reward;
    }
}
