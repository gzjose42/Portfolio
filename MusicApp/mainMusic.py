"""
Program: mainMusic.py
Author: Jose Gonzalez

Purpose: This program takes in a document with 10,000 songs and then stores
all of them into a musicLibrary object. Then it creates a graphical interface
window when the user can type in what they are looking for and grabs songs that
match the given input.

Notes:

so far I just have the input window and a button that takes the input and then
prints it into the window.

Next thing that needs to be done is to find a way to print out the song titles
and possibly turn them into buttons that will put the song name into the play
bar.


"""




from MusicLibrary import musicLibrary
from Song import *
from graphics import *



def main():
 
    lib = musicLibrary("music(updated).csv")
    print("Welcome to Treble\n\n")
    #user = input("Search: ")

    
    win = GraphWin("Treble", 500, 500)  # this creates the window and then
    inputBox = Entry(Point(47,12), 12)  # places the inputbox and the button
    inputBox.draw(win)                  # into the window

    buttonp1 = Point(95,1)
    buttonp2 = Point(115,23)
    button = Rectangle(buttonp1, buttonp2)
    button.setFill("red")
    button.draw(win)
  
    start = buttonp2.getY() + 10
    while True:
        mouse = win.getMouse()
        x = mouse.getX()
        y = mouse.getY()
        # activate the search if the mouse clicks on the red box 
        if (x >= buttonp1.getX() and x <= buttonp2.getX() ) and (y >= buttonp1.getY() and y <= buttonp2.getY()):

            text = inputBox.getText()   # the user input in the inbox

            # clear the list area
            clear1 = Point(0,start -5)
            clear2= Point(500,500)
            clear = Rectangle(clear1, clear2)
            clear.setFill("white")
            clear.setOutline('white')
            clear.draw(win)

            #returns a list of songs, artist, and genres that match the text
            match = matches(lib,text,"a")
            
            if (len(match) != 0):
                # prints out the boxes with the matches in them
                i = 0
                while i < len(match) and i < 20:
                    message = Text(Point(250, start), str(match[i]))
                    box = Rectangle(Point(80, start - 8), Point(420, start + 12))
                    box.draw(win)
                    message.draw(win)
                    start += 21
                    print(str(match[i]))
                    i+=1

        
            else:
                # prints that nothing was found
                message = Text(Point(250, start), "no song found, try again")
                box = Rectangle(Point(100, start - 8), Point(400, start + 12))
                box.draw(win)
                message.draw(win)
                print("no song found, try again")
            
            # resets positions and the input box
            inputBox.setText("")
            start = buttonp2.getY() + 10

       
    """  while (user.lower() != "done"):
        match = matches(lib,user,cate)
        
        if (len(match) != 0):
            for i in range(len(match)):
                print(str(match[i]))

        
        else:
            print("no song found, try again")

        print("Type \"done\" when finished")
        user = input("Search: ")"""


""" this method takes in the music library and the phrase the user typed in
    and then it creates a list of songs, artist, and genres that match the
    users input"""            
def matches(lib,user,cate):

    found = []
    gFind = 3 #max of 3 genre matches
    for element in lib.songlist["g"].keys():
            
            
        if (user.lower() in element.lower()) and gFind != 0:
            found += [element.upper()]
            gFind -= 1
    

    aFind = 2 #max of 2 artist matches
    for element in lib.songlist[cate].keys():
            
            
        if user.lower() in element.lower() and aFind != 0:
            found += lib.songlist[cate][element]
            aFind -= 1

    #the rest of the spaces are filled with direct song matches
    for element in lib.songlist["s"]:
            
            
        if user.lower() in element.getTitle().lower():
          
            found += [element]

    return found
    
    
main()



