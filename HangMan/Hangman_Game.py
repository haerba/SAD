'''
Created on 9 Mar 2019

@author: haerba
'''
from Hangman_Visualizer import *
import random
import os


class Hangman_Game(object):
    

    def __init__(self, name):
        self.h_Visualizer = Hangman_Visualizer(name)
        self.lifes = 10
        self.word = ""
        self.hidden_word = ""
        self.l_u = []
        self.hints = {}
        self.used_hint = True
        self.game_on = True
        self.wordDatabase()
        
    def askForLetter(self):
        print("\nInsert your letter please: ")
        letter = input()
        while(letter in self.l_u or len(letter) >1):
            if len(letter) == 1:
                self.h_Visualizer.print_usedLetters(self.l_u)
            print("\nInsert one letter please: ")
            letter = input()
        self.l_u.append(letter)
        self.compare_l(letter)
        
    
    def compare_l(self, letter):
        l_word = list(self.word)
        l_h_word = list(self.hidden_word)
        if(letter in l_word):
            for pos in range(len(l_word)):
                if letter == l_word[pos]:
                    l_h_word[pos] = letter
                    self.hidden_word = "".join(l_h_word)
        else:
            self.lifes -= 1
            self.h_Visualizer.print_wrongLetter(letter)
            
    def askForHint(self):
        letter = ''
        while(letter not in ['y', 'n']):
            print("\nPlease, answer properly:")
            self.h_Visualizer.askForHint()
            letter = input()
        if(letter == 'y'):
            print('\n'+self.hints[self.word])
            self.used_hint = False
            
    def userGame(self):
        while(self.game_on):
            if('*' not in self.hidden_word):
                self.h_Visualizer.print_Winner(self.word)
                self.game_on = False
                
            else:
                if(self.lifes > 0):
                    self.clearer()         
                    self.h_Visualizer.print_currentStatus(self.lifes, self.l_u, self.hidden_word)
                    if(self.used_hint):
                        self.askForHint()
                    self.askForLetter()
                else:
                    self.h_Visualizer.print_GameOver(self.word)
                    self.game_on = False
        self.restart()
        
            
    
    def wordDatabase(self):
        list_of_words = ["papaya", "excalibur", "pokemon", 
                         "euler", "digimon", "pirate", 
                         "integration", "derivation", "fourier"]
        ran = random.randint(0, len(list_of_words)-1)
        self.word = list_of_words[ran]
        self.hints = {"papaya":"HINT: It is a fruit", "excalibur":"HINT: It is a sword", 
                      "pokemon":"HINT: It is a fiction creature", 
                      "euler":"HINT: A great mathematician", 
                      "digimon":"HINT: It is a fiction creature", 
                      "pirate":"HINT: One piece" , "integration": "Mathematical tool", 
                      "derivation":"HINT: Mathematical tool", 
                      "fourier":"HINT: Important mathematician"}
        self.hidden_word = '*' * len(self.word)
    
    def restart(self):
        self.used_hint = True
        self.l_u = []
        self.game_on = True
        self.wordDatabase()
        self.lifes = 10
        
    def clearer(self):
        os.system("clear")
        

            
        
        
        
        
        
        
        
        