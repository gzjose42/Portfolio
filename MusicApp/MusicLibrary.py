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

from Song import Song

class musicLibrary:

    def __init__(self,musicFileName):

       self.songlist =  self.storeSongs(musicFileName)


    def storeSongs(self, musicFileName ):
        musicFile = open(musicFileName)
        lib = {}
        artistlib = {}
        yearlib = {}
        genrelib = {}
        songlib = []
        musicFile.readline()
        for musicLine in musicFile:
            musicInfo = musicLine.rstrip().split(',')

            if len(musicInfo) == 15:
                album = musicInfo[9]
                genre = musicInfo[11]
                title = musicInfo[13]
                year = musicInfo[14]
                artist = musicInfo[1]
                song = Song(title, album, artist, year, genre)
                songlib.append(song)
                if artist in artistlib.keys():
                    artistlib[artist].append(song)
                else:
                   artistlib[artist] = [song]
                if year in yearlib.keys():
                    artistlib[year].append(song)
                else:
                   artistlib[year] = [song]
                if genre in genrelib.keys():
                    genrelib[genre].append(song)
                else:
                   genrelib[genre] = [song]
        lib["a"] = artistlib
        lib["g"] = genrelib
        lib["y"] = yearlib
        lib["s"] = songlib
        return lib

            
