const displayAverageDisks = function(username) {
    const user = db.getCollection('users').findOne({ username: username });
  
    if (user) {
      const result = db.getCollection('statistics').aggregate([
        { $match: { users_user_id: user.user_id } },
        { $group: { _id: null, averageDisks: { $avg: { $toInt: "$disks" } } } }
      ]).toArray();
  
      if (result.length > 0) {
        print("Average disks for user " + username + ": " + result[0].averageDisks);
      } else {
        print("No statistics available for the user: " + username);
      }
    } else {
      print("User not found: " + username);
    }
  };
  
displayAverageDisks("sami");  // Replace with the desired username
  