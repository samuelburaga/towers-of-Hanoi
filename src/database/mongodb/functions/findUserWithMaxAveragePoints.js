const findUserWithMaxAveragePoints = function() {
    const result = db.getCollection('statistics').aggregate([
      {
        $group: {
          _id: "$users_user_id",
          averagePoints: { $avg: { $toInt: "$points" } }
        }
      },
      { $sort: { averagePoints: -1 } },
      { $limit: 1 },
      {
        $lookup: {
          from: "users",
          localField: "_id",
          foreignField: "user_id",
          as: "user"
        }
      },
      { $unwind: "$user" },
      {
        $project: {
          _id: 0,
          username: "$user.username",
          averagePoints: 1
        }
      }
    ]).toArray();
  
    if (result.length > 0) {
      print("User with the maximum average points:");
      print("Username: " + result[0].username);
      print("Average Points: " + result[0].averagePoints);
    } else {
      print("No statistics available.");
    }
  };
  
findUserWithMaxAveragePoints();
  