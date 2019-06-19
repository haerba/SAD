'''
Created on 9 Mar 2019

@author: haerba
'''

class Hangman_Visualizer(object):    
    
    def __init__(self, name):
        self.name = name
        self.wrong_letter_strings = []
        self.correct_letter_strings = []
        self.hanged_man = [] 
        self.charge_HangedMan()
    
    
    def print_currentStatus(self, lifes, l_u, hidden_word):
        print("------------------ HANGED MAN GAME ------------------","\nPlayer: "+self.name , "\nCurrent Lives: "+ str(lifes),
              "\nUsed Letters: "+ ', '.join(l_u), "\nYour word is: "+ hidden_word, "\nHANGED MAN:\n\n", self.hanged_man[10-lifes])
        
        
    def print_usedLetters(self, l_u):
        print("""Letter already used, please insert another one. 
Your used letters are:""")
        print(', '.join(l_u))
        
        
    def print_wrongLetter(self, letter):
        print("Your letter was wrong.")
            
    def charge_HangedMan(self):
        self.hanged_man.append("")
        self.hanged_man.append("______________")
        self.hanged_man.append("""
| 
|
|
|
|
|
|
|_____________

FFS pls.""")
        self.hanged_man.append("""
––––––––––––––
| 
|
|
|
|
|
|
|_____________

Just surrender pls.""")
        self.hanged_man.append("""
––––––––––––––
| /
|/
|
|
|
|
|
|_____________

It looks exactly like the ETSETB exam rooms 10 min before...""")
        self.hanged_man.append("""
––––––––––––––
| /          |
|/           O
|
|
|
|
|
|_____________

Just pray my boy.""")
        self.hanged_man.append("""
––––––––––––––
| /          |
|/           O
|            |
|
|
|
|
|_____________

Shit happens.""")
        self.hanged_man.append("""
––––––––––––––
| /          |
|/           O
|           -|
|
|
|
|
|_____________

It does not look very good man...""")
        self.hanged_man.append("""
––––––––––––––
| /          |
|/           O
|           -|-
|
|
|
|
|_____________

I can feel your death...""")
        self.hanged_man.append("""
––––––––––––––
| /          |
|/           O
|           -|-
|           / 
|
|
|
|_____________

It's getting closer!!!!!!!!""")
        self.hanged_man.append("""
––––––––––––––
| /          |
|/           O
|           -|-
|           / \
|
|
|
|_____________

You lost...""")
        
    def print_GameOver(self, word):
        print("\n\nYOU LOST! LOOOOOOOOOOOOOOOOOOOOOOOSER!!!!!", "\nThe word was "+word)
    
    def print_Winner(self, word):
        print("\n\n Beh, okay, you won. Wtf do you want? GET LOST.", "\nYour word was "+word)
    
    def askForHint(self):
        print("""Do you want a Hint?
Yes: y    No: n""")
    
    
    