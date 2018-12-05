moduloDirectivas.component('headerComponent', {
    templateUrl: 'js/app/components/header/header.html',
    controllerAs: 'c',
    controller: js
});

function js(toolService,sessionService){
    var self = this;

    self.logged = sessionService.isSessionActive();
    self.name = sessionService.getUserName();
    self.idUserLogged = sessionService.getId();
    self.isActive = toolService.isActive;

}