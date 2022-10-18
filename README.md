# Android Project 6 - *RunApp*

Submitted by: **Albi Kozeli**

**RunApp** is a health metrics app that allows users to track run activities, its distance, duration
and rate their experience. The app navigates through fragments and allows the user to view past activities 
or the activities' metrics. The user can also add run activties through a second EnterRun activity. The data
is stored in a local SQLite Database.

Time spent: **10** hours spent in total

## Required Features

The following **required** functionality is completed:

- [x] **Use at least 2 Fragments**
- [x] **Create a new dashboard fragment where users can see a summary of their entered data**
- [x] **Use one of the Navigation UI Views (BottomNavigation, Drawer Layout, Top Bar) to move between the fragments**

The following **optional** features are implemented:

- [ ] **Add a more advanced UI (e.g: Graphing) for tracking trends in metrics**
- [ ] **Implement daily notifications to prompt users to fill in their data**

The following **additional** features are implemented:

- [ ] List anything else that you can get done to improve the app functionality!

## Video Walkthrough

Here's a walkthrough of implemented user stories:

![walkthrough](https://user-images.githubusercontent.com/98725619/196291068-f18dbc4d-e987-4e80-9841-985d2d9e6994.gif)


<!-- Replace this with whatever GIF tool you used! -->
GIF created with LICEcap 
<!-- Recommended tools:
[Kap](https://getkap.co/) for macOS
[ScreenToGif](https://www.screentogif.com/) for Windows
[peek](https://github.com/phw/peek) for Linux. -->

##2 Fragments

Here is the changing between fragments code in the Main Activity
![image](https://user-images.githubusercontent.com/98725619/196522640-36de59c3-96b5-4ffc-9f19-0095cfa37bf7.png)

Here is the main activity xml file
![image](https://user-images.githubusercontent.com/98725619/196522946-6aa28dfb-92ec-4a7b-8ff7-6f79f4afb9cd.png)

Here is the (first) RunFragment
![image](https://user-images.githubusercontent.com/98725619/196523240-0f279883-0c60-4b81-a0b3-be9989954c2c.png)
![image](https://user-images.githubusercontent.com/98725619/196523318-d3b84cd5-0194-4ce5-8eb2-fb8b572a24c7.png)

Here is the (second) MetricsFragment
![image](https://user-images.githubusercontent.com/98725619/196523569-25dcc7a6-f74e-4ca9-8e8d-30051f98c90c.png)
![image](https://user-images.githubusercontent.com/98725619/196523663-cf26558f-7f17-4585-a6c8-50fa761481c4.png)

## Notes

A challenging part was writting to and reading from the database using two different activities and two different fragments.

## License

    Copyright [2022] [Albi Kozeli]

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

        http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
