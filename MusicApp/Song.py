
""" This is a simple class that just holds information
    about the song """
class Song:

    def __init__(self, title, album, artist, year, genre):
        self.Title = title
        self.Album = album
        self.Artist = artist
        self.Year = year
        self.Genre = genre


    """ these methods get the values in the fields"""    
    def getTitle(self):
        return self.Title
    def getAlbum(self):
        return self.Album  
    def getArtist(self):
        return self.Artist
    def getYear(self):
        return self.Year
    def getGenre(self):
        return self.Genre

    def __str__(self):
        return self.Title + " by: " + self.Artist
