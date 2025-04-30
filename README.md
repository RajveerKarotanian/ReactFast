# React Fast! - Reaction Time Tester Game  

## Description  
React Fast! is a Java-based reaction time tester game where players must press the spacebar at the precise moment when a moving red bar touches a green box. The game features multiple difficulty levels, score tracking, and visual feedback.  

The game window includes:  
- A play area with moving components  
- Difficulty selector (Easy, Normal, Hard)  
- Start/Restart button  
- Current score display  
- High score tracker  
- Animated face panel that reacts to game states  

## How to Run  
1. Navigate to the `ReactFast` folder  
2. Run the `ReactFast` class  

## Gameplay Instructions  
1. Select your desired difficulty level (affects the speed of the red bar)  
2. Click "Start" to begin the game  
3. Watch as the red bar moves back and forth across the play area  
4. When a green box appears, press the spacebar when the red bar touches it  
5. Each successful hit increases your score by 1  
6. The game ends if you press spacebar at the wrong time (score resets to 0)  
7. Click "Restart" at any time to reset the game  

## Requirements  
- Java 8 or later  
- Swing library (included in standard Java distributions)  

## Notes  
- The high score persists while the game is running but resets when the program is closed  
- The face animation changes based on game state:  
  - **Happy (Green)** - Game is running and player is scoring  
  - **Neutral (Orange)** - Game is idle (not started)  
  - **Sad (Red)** - Player made a wrong move  

## Files  
- `ReactFast.java` - Main game class  
- `PlayArea.java` - Gameplay component  
- `Face.java` - Animated face panel  
- `Text.java` - Custom score display  

Developed using Java Swing for GUI components and event handling.
