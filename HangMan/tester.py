'''
Created on 9 Mar 2019

@author: haerba
'''
from Hangman_Visualizer import *


if __name__ == '__main__':
    """listeta = ['a', 'b', 'c', 'd']
    print(listeta)
    h_Visualizer = Hangman_Visualizer("Hamza")
    h_Visualizer.print_usedLetters(listeta)
    """
    
    """letter = input()
    word = list("passotisme")
    hidden_word = list("**********")
    if letter in word:
            for pos in range(len(word)):
                print(word[pos])
                if letter == word[pos]:
                    hidden_word[pos] = letter
    
    word = "".join(word)
    hidden_word = "".join(hidden_word)
    print(letter, word, hidden_word)
    """
    
    """
    h_Visualizer = Hangman_Visualizer("Hamza")
    h_Visualizer.print_CurrentStatus( 2 , ['a', 'b', 'c', 'd'], '***a**ss')
    """
    """
    hidden_word = '*'*10
    print(hidden_word)
    """
    
    print("Do you want to play again?", "Yes: y    No: n")
    letter = ''
    print(letter)
    while(letter not in ['y', 'n']):
        print("Please, answer properly:")
        letter = input()
        print(letter)
    if(letter == 'n'):
        print("EOG")
    
    
