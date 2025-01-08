# Simple-ffmpeg-front-end
The simplest ffmpeg front end ever

I suppose it is meant for Windows machines, but since it is Java so it might work with other Operating Systems. To make it work in linux you can rename your CJXL binary to cjxl.exe, it might work, let me know!

Having thousands of old movies I want to convert using the latest codecs that provide better compression, I wanted to use a front end to run the command ffmpeg.exe. Just a few clicks to convert without any arguments, but for some reason there were none. WinFF was great, but it is discontinued and does not switch codecs. So here it is, a very simple front end using Java that uses ffmpeg.exe to convert your library of movies using latest codecs. Call this the very simplified spiritual successor to WinFF.

Steps to setup the first time:
------------------------------
![image](https://github.com/user-attachments/assets/993e1eac-3f80-437d-9924-d342991d80c4)

0. Obtain ffmpeg binaries from https://ffmpeg.org
1. Select folder where ffmpeg.exe is located
2. Set input command argument. These are arguments that will be passed onto the command. Leave it as it is for basic users
3. Set output command argument. These are arguments that will be passed onto the command. Leave it as it is for basic users
And you are done! Start using!

Steps to use (basic user):
--------------------------
4. Select source folder where videos you want to convert are located
5. Select target folder where you want the converted videos to be stored. If same as source folder then leave empty.
6. Select the codec supported by ffmpeg.
   If upgrading to a newer release of ffmpeg and the program is not able to obtain the list from ffmpeg.exe automatically, you may type in the codec manually.
8. Select option to:
   - Delete original videos after conversion (use with caution)
   - Restore Create and Modified date of source videos to converted video files
9. Convert away!
10. You can play the video file from this UI. I actually use this quite often, it is now my mini video player :)

Steps to use (advanced user):
-----------------------------
For advanced user who want to control the ffmpeg.exe command, buttons 4 and 5 controls the arguments to the command line. 
![image](https://github.com/user-attachments/assets/bd6793fc-59cd-428f-9820-f002d501e3d3)
Simply ensure the custom arguments are put before -i (for input) and -c:v for output
![image](https://github.com/user-attachments/assets/611c2bc0-db59-4662-94fe-e4bf6753d912)
And you should be good to go!

Disclaimer: Use at your own risk! I will not be held responsible of any negative outcomes, damage or loss of life or property caused directly or indirectly from this program. However if you want, feel free to buy me a cup of coffee!
