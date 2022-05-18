package game;

/**
 * Use this enum class to give `buff` or `debuff`.
 * It is also useful to give a `state` to abilities or actions that can be attached-detached.
 */
public enum Status {
    HOSTILE_TO_ENEMY,   // use this status to be considered hostile towards enemy (e.g., to be attacked by enemy)
    TRADE,              // use this status to allow actor to engage in trade.
    CONVERSES,          // use this status to allow actor to engage in conversations
    ON_HIGH_GROUND,     // use this status to tell that an actor is on a high_ground
    RESETTABLE,         // use this status to tell that an actor is able to use reset action

    TALL,               // use this status to tell that current instance has "grown".
    POWER_STAR,         // use this status to tell that an actor is invincible

    ATTACKED,           // use this status to tell that an actor just attacked (mainly for enemies)
    GOT_ATTACKED,       // use this status to tell that an actor just got attacked (mainly for enemies)
    HOSTAGE,
    FLY,

    CAN_SLEEP,          // use this status to tell that an actor can go to sleep after becoming unconscious
    DORMANT,            // use this status to tell that an actor is asleep

    CARRIED,            // use this status to describe an item in the inventory
    BREAK_SHELL,        // use this status to describe an item that can break Koopa's shell.
    HERO,

    FERTILE,            // use this status to describe fertile grounds that can grow plants (dirt)
    HIGH_GROUND,        // use this status to describe a jumpable ground (e.g., sprouts, saplings, trees, walls)
    PIRANHA,            // use this status to describe a warp pipe with a piranha plant that's alive

    CAN_BE_CONSUMED

}
