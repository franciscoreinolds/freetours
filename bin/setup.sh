#!/bin/bash

set -e
. "functions.sh"

pp_info "setup" "Installing Required Tools"

if not_installed "docker"; then
  pp_error "setup" "We are using docker for postgres container, since it was not found on your system we cannot ensure that you are using the correct versions of all the tools."
  ensure_confirmation
  pp_info "setup" "We are installing docker"
  sudo apt-get update
  sudo apt-get install \
    apt-transport-https \
    ca-certificates \
    curl \
    gnupg-agent \
    software-properties-common
  curl -fsSL https://download.docker.com/linux/ubuntu/gpg | sudo apt-key add -
  sudo apt-key fingerprint 0EBFCD88
  sudo add-apt-repository \
   "deb [arch=amd64] https://download.docker.com/linux/ubuntu \
   $(lsb_release -cs) \
   stable"
  sudo apt-get update
  sudo apt-get install docker-ce docker-ce-cli containerd.io
  pp_info "setup" "Enabling Docker to start on boot."
  sudo systemctl start docker
  sudo systemctl enable docker
  sudo usermod -a -G docker $USER
  pp_success "setup" "docker is up-and-running"
else
  docker_state=$(sudo docker info >/dev/null 2>&1)
  if [[ $? -ne 0 ]]; then
    pp_warn "setup" "docker does not seem to be running, run it first and retry"
    exit 1
  else
    pp_success "setup" "docker is up-and-running"
  fi
fi

if not_installed "npm"; then
  pp_error "setup" "We are using NPM for managing packages for the JavaScript, since it was not found on your system we cannot ensure that you are using the correct versions of all the tools."
  ensure_confirmation
  curl -sL https://deb.nodesource.com/setup_10.x | sudo -E bash -
  sudo apt install nodejs
  sudo npm cache clean -f
  sudo npm install -g n
  sudo n stable
else
  pp_success "setup" "NPM is already installed"
fi

pp_info "setup" "Installing Required Languages and frameworks"

if not_installed "java"; then
  pp_error "setup" "We are using Java for our backend, since it was not found on your system we cannot ensure that you are using the correct versions of all the tools."
  ensure_confirmation
  sudo apt-get update
  sudo apt-get install default-jre
  sudo apt-get install default-jdk
else
  pp_success "setup" "Java is already installed"
fi

if not_installed "mvn"; then
  pp_error "setup" "We are using Maven for our backend, since it was not found on your system we cannot ensure that you are using the correct versions of all the tools."
  ensure_confirmation
  sudo apt-get update
  sudo apt-get install maven
else
  pp_success "setup" "Maven is already installed"
fi

pp_info "setup" "Creating a PostgreSQL container"
sudo docker container inspect postgres > /dev/null 2>&1 || sudo docker run -d --name postgres -p 5432:5432 postgres:11.5

pp_success "setup" "You're good to go!"
