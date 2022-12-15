# ListWiz

ListWiz is a ToDo list app! A user can set their username and team and create tasks that will appear on their homepage.

## Features

- Scroll through tasks
- Save your username and team
- Login/ signup with Cognito
- Save Tasks to DynamoDB
- Create new tasks
- Set task status
- Set task team
- Set task image
- View task details w/ images from s3
- View toasts

## Screenshots

![nah](/app/screenshots/main.png)

![nah](/app/screenshots/addTask.png)

![nah](/app/screenshots/all.png)

![nah](/app/screenshots/taskDetail.png)

![nah](/app/screenshots/profile.png)

![nah](/app/screenshots/signup.png)

![nah](/app/screenshots/signin.png)


#### Changelog

- 12/1 -> Adds three initial pages and links them together
- 12/4 -> Adds username saving and task details page
- 12/5 -> Replaces hardcoded buttons on main with hardcoded recycler view
- 12/6 -> Local database integration, set task status
- 12/8 -> Removes local databse integration, adds aws amplify integration
- 12/12 -> Adds task owners/teams to db structure. Changes reflected on: AddTask, UserProfile, and Main
- 12/13 -> Enables signup/login with cognito. No cognito username display...
- 12/14 -> Adds option for task images using S3 integration. These images are viewable on the tasks details page
