# Kae-Rene's Personal Project: First World Problems
## A Visual Media Tracker for Disaster Children

**First World Problems** is a movie and TV show tracker. It's primary purpose is to **allow individuals with 
subscriptions to multiple streaming platforms to store all their current viewings in one place.** This reduces
the need for the users to remember which streaming platform they were watching a particular show on. It also allows for 
users to easily identify shows that they have yet to complete if they went on a viewing break.

This application can be used by anyone but is specifically *targeted at those who are inconsistent with their viewing 
habits, as well as their media sources*. As someone whose interest in shows fluctuates like Vancouver's weather, but 
hates not finishing a series I've started watching, this application is something I am in much need of. 

## User Stories
1. As a user, I want to be able to add a new movie/tv show
2. As a user, I want to be able to mark the viewing status -- 'to watch', 'watching', 'watched'
3. As a user, I want to be able to modify the viewing status
4. As a user, I want to be able to record the streaming platform where the piece of media is available to me
5. As a user, I want to be able to save my media list to file
6. As a user, I want to be able to load my media list from file upon application start up

## Console Tests Log

Tue Nov 23 21:42:02 PST 2021
Media Added.

Tue Nov 23 21:42:09 PST 2021
Media Status Updated.

Tue Nov 23 21:42:13 PST 2021
Media Removed.

## Documentation of Future Improvements
As seen in the UML diagram, an abstract class Media was created to refactor duplicate code in Movie and Show. However, 
Media has a Status field of type String. I believe that this field could be made into a Status enum class instead that 
had the values 'To Watch', 'Watching', and 'Watched'. This would  make it easier to standardise the status, as well as a 
smoother transition between the console interface implementation and GUI. 