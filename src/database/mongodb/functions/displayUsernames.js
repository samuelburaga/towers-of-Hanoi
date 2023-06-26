const displayUsernames = function() {
    db.getCollection('users').find({}, { username: 1 }).forEach(function(user) {
      print(user.username);
    });
  };
  
displayUsernames();  