###! to be removed before submission !
## process
- move MediaTrackerApp menus into MediaTrackerUI as GUI
- add mouse listener (desktop.addMouseListener) for menu navigation stuff
- add key listener (addKeyListener) for field data inputs

## code snippets
### action template
~~~
/**
* Represents action to be taken when user wants to <INSERT ACTION HERE>
* to the system.
*/

private class *ActionName* extends AbstractAction {

    AddCodeAction() {
        super("ProcessLabel");
    }
    
    @Override //STUB
    public void actionPerformed(ActionEvent evt) {
        // any set up things go here
        
        try {
            // do the process here
        } catch (ActionFailedException e) {
           // exception thrown proecess here
        }
    }
}
~~~
note: not all actionPerformed methods require a try-catch block