When an object is initialised such as:

DEL.User user = new User();

It is transient and is not associated with the database.
As soon as the object is saved such as

session.Save(user);

The user object is now persistant for the duration of the transaction(until session.close() is called) or
when the control leaves a @transactional annotated method. When this happens, ie the transaction completes, 
the object user is now detatched. This means its not associated with the database and any changes aren't
synchronised back to the database. This object might have data from the database in it, ie it was but it wont
be synchroned until it is made persistant again.

