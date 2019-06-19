'''
Created on 9 Mar 2019

@author: haerba
'''
from Hangman_Game import *
import pygame

if __name__ == '__main__':
    variable = True
    pygame.mixer.init()
    pygame.mixer.music.load("arcade_song.mp3")
    pygame.mixer.music.play()
    os.system("clear")
    print("User, insert your name please:")
    name = input()
    hangman_gamer = Hangman_Game(name)
    while(variable):
        hangman_gamer.userGame()
        print("Do you want to play again?", "Yes: y    No: n")
        letter = ''
        while(letter not in ['y', 'n']):
            print("Please, answer properly:")
            letter = input()
        if(letter == 'n'):
            variable = False
        
    
    