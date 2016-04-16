<<<<<<< HEAD
[![MEAN.JS Logo](http://meanjs.org/img/logo-small.png)](http://meanjs.org/)

[![Gitter](https://badges.gitter.im/Join Chat.svg)](https://gitter.im/meanjs/mean?utm_source=badge&utm_medium=badge&utm_campaign=pr-badge&utm_content=badge)
[![Build Status](https://travis-ci.org/meanjs/mean.svg?branch=master)](https://travis-ci.org/meanjs/mean)
[![Dependencies Status](https://david-dm.org/meanjs/mean.svg)](https://david-dm.org/meanjs/mean)
[![Coverage Status](https://coveralls.io/repos/meanjs/mean/badge.svg?branch=master&service=github)](https://coveralls.io/github/meanjs/mean?branch=master)

MEAN.JS is a full-stack JavaScript open-source solution, which provides a solid starting point for [MongoDB](http://www.mongodb.org/), [Node.js](http://www.nodejs.org/), [Express](http://expressjs.com/), and [AngularJS](http://angularjs.org/) based applications. The idea is to solve the common issues with connecting those frameworks, build a robust framework to support daily development needs, and help developers use better practices while working with popular JavaScript components.

## Before You Begin
Before you begin we recommend you read about the basic building blocks that assemble a MEAN.JS application:
* MongoDB - Go through [MongoDB Official Website](http://mongodb.org/) and proceed to their [Official Manual](http://docs.mongodb.org/manual/), which should help you understand NoSQL and MongoDB better.
* Express - The best way to understand express is through its [Official Website](http://expressjs.com/), which has a [Getting Started](http://expressjs.com/starter/installing.html) guide, as well as an [ExpressJS](http://expressjs.com/en/guide/routing.html) guide for general express topics. You can also go through this [StackOverflow Thread](http://stackoverflow.com/questions/8144214/learning-express-for-node-js) for more resources.
* AngularJS - Angular's [Official Website](http://angularjs.org/) is a great starting point. You can also use [Thinkster Popular Guide](http://www.thinkster.io/), and [Egghead Videos](https://egghead.io/).
* Node.js - Start by going through [Node.js Official Website](http://nodejs.org/) and this [StackOverflow Thread](http://stackoverflow.com/questions/2353818/how-do-i-get-started-with-node-js), which should get you going with the Node.js platform in no time.


## Prerequisites
Make sure you have installed all of the following prerequisites on your development machine:
* Node.js - [Download & Install Node.js](https://nodejs.org/en/download/) and the npm package manager. If you encounter any problems, you can also use this [GitHub Gist](https://gist.github.com/isaacs/579814) to install Node.js.
  * Node v5 IS NOT SUPPORTED AT THIS TIME! 
* MongoDB - [Download & Install MongoDB](http://www.mongodb.org/downloads), and make sure it's running on the default port (27017).
* Ruby - [Download & Install Ruby](https://www.ruby-lang.org/en/documentation/installation/)
* Bower - You're going to use the [Bower Package Manager](http://bower.io/) to manage your front-end packages. Make sure you've installed Node.js and npm first, then install bower globally using npm:

```bash
$ npm install -g bower
```

* Grunt - You're going to use the [Grunt Task Runner](http://gruntjs.com/) to automate your development process. Make sure you've installed Node.js and npm first, then install grunt globally using npm:

```bash
$ npm install -g grunt-cli
```

* Sass - You're going to use [Sass](http://sass-lang.com/) to compile CSS during your grunt task. Make sure you have ruby installed, and then install Sass using gem install:

```bash
$ gem install sass
```

* Gulp - (Optional) You may use Gulp for Live Reload, Linting, and SASS or LESS.

```bash
$ npm install gulp -g
```

## Downloading MEAN.JS
There are several ways you can get the MEAN.JS boilerplate:

### Cloning The GitHub Repository
The recommended way to get MEAN.js is to use git to directly clone the MEAN.JS repository:

```bash
$ git clone https://github.com/meanjs/mean.git meanjs
```

This will clone the latest version of the MEAN.JS repository to a **meanjs** folder.

### Downloading The Repository Zip File
Another way to use the MEAN.JS boilerplate is to download a zip copy from the [master branch on GitHub](https://github.com/meanjs/mean/archive/master.zip). You can also do this using `wget` command:

```bash
$ wget https://github.com/meanjs/mean/archive/master.zip -O meanjs.zip; unzip meanjs.zip; rm meanjs.zip
```

Don't forget to rename **mean-master** after your project name.

### Yo Generator
Another way would be to use the [Official Yo Generator](http://meanjs.org/generator.html), which generates a copy of the MEAN.JS 0.4.x boilerplate and supplies an application generator to ease your daily development cycles.

## Quick Install
Once you've downloaded the boilerplate and installed all the prerequisites, you're just a few steps away from starting to develop your MEAN application.

The first thing you should do is install the Node.js dependencies. The boilerplate comes pre-bundled with a package.json file that contains the list of modules you need to start your application. To learn more about the modules installed visit the npm & Package.json section.

To install Node.js dependencies you're going to use npm again. In the application folder run this in the command-line:

```bash
$ npm install
```

This command does a few things:
* First it will install the dependencies needed for the application to run.
* If you're running in a development environment, it will then also install development dependencies needed for testing and running your application.
* Finally, when the install process is over, npm will initiate a bower install command to install all the front-end modules needed for the application

## Running Your Application
After the install process is over, you'll be able to run your application using Grunt, just run grunt default task:

```
$ grunt
```

Your application should run on port 3000 with the *development* environment configuration, so in your browser just go to [http://localhost:3000](http://localhost:3000)

That's it! Your application should be running. To proceed with your development, check the other sections in this documentation.
If you encounter any problems, try the Troubleshooting section.

* explore `config/env/development.js` for development environment configuration options

### Running in Production mode
To run your application with *production* environment configuration, execute grunt as follows:

```bash
$ grunt prod
```

* explore `config/env/production.js` for production environment configuration options

### Running with User Seed
To have default account(s) seeded at runtime:

In Development:
```bash
MONGO_SEED=true grunt
```
It will try to seed the users 'user' and 'admin'. If one of the user already exists, it will display an error message on the console. Just grab the passwords from the console.

In Production:
```bash
MONGO_SEED=true grunt prod
```
This will seed the admin user one time if the user does not already exist. You have to copy the password from the console and save it.

### Running with TLS (SSL)
Application will start by default with secure configuration (SSL mode) turned on and listen on port 8443.
To run your application in a secure manner you'll need to use OpenSSL and generate a set of self-signed certificates. Unix-based users can use the following command:

```bash
$ sh ./scripts/generate-ssl-certs.sh
```

Windows users can follow instructions found [here](http://www.websense.com/support/article/kbarticle/How-to-use-OpenSSL-and-Microsoft-Certification-Authority).
After you've generated the key and certificate, place them in the *config/sslcerts* folder.

Finally, execute grunt's prod task `grunt prod`
* enable/disable SSL mode in production environment change the `secure` option in `config/env/production.js`


## Testing Your Application
You can run the full test suite included with MEAN.JS with the test task:

```bash
$ grunt test
```

This will run both the server-side tests (located in the app/tests/ directory) and the client-side tests (located in the public/modules/*/tests/).

To execute only the server tests, run the test:server task:

```bash
$ grunt test:server
```

And to run only the client tests, run the test:client task:

```bash
$ grunt test:client
```

## Running your application with Gulp

After the install process, you can easily run your project with:

```bash
$ gulp
```
or

```bash
$ gulp default
```

The server is now running on http://localhost:3000 if you are using the default settings. 

### Running Gulp Development Environment

Start the development environment with:

```bash
$ gulp dev
```

### Running in Production mode
To run your application with *production* environment configuration, execute gulp as follows:

```bash
$ gulp prod
```

### Testing Your Application with Gulp
Using the full test suite included with MEAN.JS with the test task:

### Run all tests
```bash
$ gulp test
```

### Run server tests
```bash
gulp test:server
```

### Run client tests
```bash
gulp test:client
```

### Run e2e tests
```bash
gulp test:e2e
```

## Development and deployment With Docker

* Install [Docker](https://docs.docker.com/installation/#installation)
* Install [Compose](https://docs.docker.com/compose/install/)

* Local development and testing with compose:
```bash
$ docker-compose up
```

* Local development and testing with just Docker:
```bash
$ docker build -t mean .
$ docker run -p 27017:27017 -d --name db mongo
$ docker run -p 3000:3000 --link db:db_1 mean
$
```

* To enable live reload, forward port 35729 and mount /app and /public as volumes:
```bash
$ docker run -p 3000:3000 -p 35729:35729 -v /Users/mdl/workspace/mean-stack/mean/public:/home/mean/public -v /Users/mdl/workspace/mean-stack/mean/app:/home/mean/app --link db:db_1 mean
```

## Getting Started With MEAN.JS
You have your application running, but there is a lot of stuff to understand. We recommend you go over the [Official Documentation](http://meanjs.org/docs.html).
In the docs we'll try to explain both general concepts of MEAN components and give you some guidelines to help you improve your development process. We tried covering as many aspects as possible, and will keep it updated by your request. You can also help us develop and improve the documentation by checking out the *gh-pages* branch of this repository.

## Community
* Use the [Official Website](http://meanjs.org) to learn about changes and the roadmap.
* Join #meanjs on freenode.
* Discuss it in the new [Google Group](https://groups.google.com/d/forum/meanjs)
* Ping us on [Twitter](http://twitter.com/meanjsorg) and [Facebook](http://facebook.com/meanjs)

## Contributing
We welcome pull requests from the community! Just be sure to read the [contributing](https://github.com/meanjs/mean/blob/master/CONTRIBUTING.md) doc to get started.

## Deploying To Cloud Foundry

Cloud Foundry is an open source platform-as-a-service (PaaS).  The MEANJS project
can easily be deployed to any Cloud Foundry instance.  The easiest way to deploy the
MEANJS project to Cloud Foundry is to use a public hosted instance.  The two most popular
instances are [Pivotal Web Services](https://run.pivotal.io/) and
[IBM Bluemix](https://bluemix.net).  Both provide free trials and support pay-as-you-go models
for hosting applications in the cloud.  After you have an account follow the below steps to deploy MEANJS.

* Install the [Cloud Foundry command line tools](http://docs.cloudfoundry.org/devguide/installcf/install-go-cli.html).
* Now you need to log into Cloud Foundry from the Cloud Foundry command line.
  *  If you are using Pivotal Web Services run `$ cf login -a api.run.pivotal.io`.
  *  If you are using IBM Bluemix run `$ cf login -a api.ng.bluemix.net`.
* Create a Mongo DB service.
+  *  If you are using Pivotal Web Services run `$ cf create-service mongolab sandbox mean-mongo`
+  *  If you are using IBM Bluemix run `$ cf create-service mongodb 100 mean-mongo`
* Clone the GitHub repo for MEANJS if you have not already done so
  * `$ git clone https://github.com/meanjs/mean.git && cd mean`
* Run `$ npm install`
* Run the Grunt Build task to build the optimized JavaScript and CSS files
  * `$ grunt build`
* Deploy MEANJS to Cloud Foundry
  * `$ cf push`

After `cf push` completes you will see the URL to your running MEANJS application 
(your URL will be different).

    requested state: started
    instances: 1/1
    usage: 128M x 1 instances
    urls: mean-humbler-frappa.mybluemix.net

Open your browser and go to that URL and your should see your MEANJS app running!

###  Deploying MEANJS To IBM Bluemix
IBM Bluemix is a Cloud Foundry based PaaS.  By clicking the button below you can signup for Bluemix and deploy
a working copy of MEANJS to the cloud without having to do the steps above.

[![Deploy to Bluemix](https://bluemix.net/deploy/button.png)](https://bluemix.net/deploy?repository=https%3A%2F%2Fgithub.com%2Fmeanjs%2Fmean)

After the deployment is finished you will be left with a copy of the MEANJS code in your own private Git repo
in Bluemix complete with a pre-configured build and deploy pipeline.  Just clone the Git repo, make your changes, and
commit them back.  Once your changes are committed, the build and deploy pipeline will run automatically deploying
your changes to Bluemix.

## Credits
Inspired by the great work of [Madhusudhan Srinivasa](https://github.com/madhums/)
The MEAN name was coined by [Valeri Karpov](http://blog.mongodb.org/post/49262866911/the-mean-stack-mongodb-expressjs-angularjs-and)

## License
(The MIT License)

Permission is hereby granted, free of charge, to any person obtaining
a copy of this software and associated documentation files (the
'Software'), to deal in the Software without restriction, including
without limitation the rights to use, copy, modify, merge, publish,
distribute, sublicense, and/or sell copies of the Software, and to
permit persons to whom the Software is furnished to do so, subject to
the following conditions:

The above copyright notice and this permission notice shall be
included in all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED 'AS IS', WITHOUT WARRANTY OF ANY KIND,
EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF
MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT.
IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY
CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT,
TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE
SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
=======
# Team CiCi
![Home Page](https://raw.githubusercontent.com/qianwang1013/LoveReminder/master/LoveReminder_Mobile/app/src/main/res/drawable/lovelogo.jpg)
======================

Team Member Information

**Members**|**Username**
-----------|-------------
Qian Wang   | qianwang1013
Abel Mak|amak07
Dylan Sayre|Dsayre
Lin Zhou|ZhouLin1992

**Basic Information**
-----------------------

**LoveReminder** - has a Web component as well as an Android component.

    *Web Interface* 
   
    1. using [Meanjs] (http://meanjs.org/) for quickly setup the backend logic and easily communicate with the data storage. 


    *Android Interface*

    1. [Estimote SDK for Android] (https://github.com/estimote/android-sdk#quick-start-for-eddystone) for setup and register [Beacons] (https://developers.google.com/beacons/)
    2. [Nearby Messages API] (https://developers.google.com/nearby/messages/overview) for exchange data between beacons

**Proposal**
-----------------------

Dating can be tricky, especially when you are trying to figure out if someone is the right match. Even the great Shakespeare once said, “Love is a smoke and is made with the fume of sighs”. But things have changed, technology can send people into outer space, it can also make it easier for people to find love. Technology did help us, it gave us Tinder, eHarmony and etc. Since then people no longer need to go to places to find their better half, instead, they sit at home busy swiping left or right. One might ask where is the action; where is the passion; where is the thrill of meeting new people and wondering if he or she is the Mr. or Ms. Right? There is none.


Nowadays, online dating experience is highly static since people need to browse through the dating websites for others’ detailed profile in order to find an interesting one. Moreover, time spent on searching and matching on those websites is dramatically waste from time to time. What if someone comes across their crush face to face and is afraid to ask for numbers? What if someone is attractive in terms of appearance but actually is not a match of characteristic at all? What if someone knows that other half decreed by fate is somewhere in the crowd nearby? Obviously they need to know the exact location. LoveReminder is here for solving these problems.


We propose building an Android application -- LoveReminder, as the solution for this problem. Add action and mystery back into dating. Our solution takes advantage of Google Beacon Platform and Nearby API technology, the first one provides us a strong context signal in the form of Bluetooth low energy(BLE) beacons and the latter gives us the option to detect those beacons in a certain range. With the help of these two IoT technologies, we are able to create a more mobile environment while keeping all the simplicity that other existing dating agent provides.


Love should come when you are least expecting. We will add that exciting element into our application. You might be get matched, when you are getting your first Starbucks coffee of the day or when you are in the library studying for the final. That is something that other dating application out there cannot provide.

*[Full Proposal] (https://docs.google.com/document/d/1wZce8KDk3NQcjSD7iqZI0Oo6CTt6btWHXxgdEo5AcBk/edit?usp=sharing)* - please see here
>>>>>>> 27cbcc121044e88248ea848b436f2e7620cbd835
