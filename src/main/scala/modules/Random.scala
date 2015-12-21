package modules

import irc.info.Info
import irc.message.{MessageCommands, Message}
import irc.server.ServerResponder
import ircbot.{BotCommand, BotModule}


class Random extends BotModule{
  override val commands: Map[String, Array[String]] = Map("slap" -> Array("Slap some sense into a user"))

  override val adminCommands: Map[String, Array[String]] = Map("triggergen2" -> Array("Trigger installgen2"),
    "banall" -> Array("Ban all users in the channel"),
    "summongary" -> Array("Prints gary in huge ASCII letters"))

  override def parse(m: Message, b: BotCommand, r: ServerResponder): Unit = {
    val target = if(!m.params.first.startsWith("#")) m.sender.nickname else m.params.first

    if(b.command == "slap"){
      if(b.hasParams) r.action(target,s"slaps ${b.paramsString}")
    }





    if(b.command == "triggergen2" && m.sender.isAdmin){
      for {
        info <- Info.get(m.server)
        channel <- info.findChannel(m.params.first)
      } yield {
        val users = channel.users
        users.foreach(user => {
          val username = user._1
          if (user._2.modes.contains("~")) {
            r.send(s"MODE ${m.params.first} +aohv $username $username $username $username")
          }
          else if (user._2.modes.contains("&")) {
            r.send(s"MODE ${m.params.first} +ohv $username $username $username")
          }
          else if (user._2.modes.contains("@")) {
            r.send(s"MODE ${m.params.first} +hv $username $username")
          }
          else if (user._2.modes.contains("%")) {
            r.send(s"MODE ${m.params.first} +v $username")
          }
        })
      }
    }

    if(b.command == "banall" && m.sender.isAdmin){
      for {
        info <- Info.get(m.server)
        channel <- info.findChannel(m.params.first)
      } yield {
        val users = channel.users
        users.foreach(user => {
          r.send(s"MODE ${m.params.first} +bb ${user._2.nickname}!*@* @${user._2.host}")
        })
      }
    }

    if(m.params.first.startsWith("#")){
      if(m.trailing.toLowerCase.trim == s"ayy ${m.config.getNickname.toLowerCase}"){
        r.pm(m.target, m.trailing.toLowerCase.replace(m.config.getNickname.toLowerCase, m.sender.nickname))
      }
    }

    if(b.command == "summongary" && m.sender.isAdmin){
      r.say(target, "        GGGGGGGGGGGGG               AAA               RRRRRRRRRRRRRRRRR   YYYYYYY       YYYYYYY")
      r.say(target, "     GGG::::::::::::G              A:::A              R::::::::::::::::R  Y:::::Y       Y:::::Y")
      r.say(target, "   GG:::::::::::::::G             A:::::A             R::::::RRRRRR:::::R Y:::::Y       Y:::::Y")
      r.say(target, "  G:::::GGGGGGGG::::G            A:::::::A            RR:::::R     R:::::RY::::::Y     Y::::::Y")
      r.say(target, " G:::::G       GGGGGG           A:::::::::A             R::::R     R:::::RYYY:::::Y   Y:::::YYY")
      r.say(target, "G:::::G                        A:::::A:::::A            R::::R     R:::::R   Y:::::Y Y:::::Y   ")
      r.say(target, "G:::::G                       A:::::A A:::::A           R::::RRRRRR:::::R     Y:::::Y:::::Y    ")
      r.say(target, "G:::::G    GGGGGGGGGG        A:::::A   A:::::A          R:::::::::::::RR       Y:::::::::Y     ")
      r.say(target, "G:::::G    G::::::::G       A:::::A     A:::::A         R::::RRRRRR:::::R       Y:::::::Y      ")
      r.say(target, "G:::::G    GGGGG::::G      A:::::AAAAAAAAA:::::A        R::::R     R:::::R       Y:::::Y       ")
      r.say(target, "G:::::G        G::::G     A:::::::::::::::::::::A       R::::R     R:::::R       Y:::::Y       ")
      r.say(target, " G:::::G       G::::G    A:::::AAAAAAAAAAAAA:::::A      R::::R     R:::::R       Y:::::Y       ")
      r.say(target, "  G:::::GGGGGGGG::::G   A:::::A             A:::::A   RR:::::R     R:::::R       Y:::::Y       ")
      r.say(target, "   GG:::::::::::::::G  A:::::A               A:::::A  R::::::R     R:::::R    YYYY:::::YYYY    ")
      r.say(target, "     GGG::::::GGG:::G A:::::A                 A:::::A R::::::R     R:::::R    Y:::::::::::Y    ")
      r.say(target, "        GGGGGG   GGGGAAAAAAA                   AAAAAAARRRRRRRR     RRRRRRR    YYYYYYYYYYYYY   ")
    }



    if(b.command == "sata" && m.sender.isAdmin){
r.say(target, "((((((((/,")
r.say(target, "((((((((((((/,")
r.say(target, "(((((((((((((((//")
r.say(target, "(((((((((((((((((((/.")
r.say(target, "(((((((((((((((((((((((/.")
r.say(target, "(((((((((((((((((((((((((((*")
r.say(target, "(((((((((((((((((((((((((((((//")
r.say(target, "(((((((((((((((((((((((((((((((((/,")
r.say(target, "(((((((((((((((((((((((((((((((((((((/.                               #%%%#*")
r.say(target, "((((((((((((((((((((((((((((((((((((((((/,         .(###(*          #%%%%%%%#%(.")
r.say(target, "(((((((((((((((((((((((((((((((((((((((((((/*   .%%&&%%%%%#(/     #%%%%%&%%%%%%&%#*")
r.say(target, "((((((((((((((((((((((((((((((((((((((((((((((%%&&&&&&&&&%%%%#((#%%%%%%%&%%&&&@&&&&%%(")
r.say(target, "((((((((((((((((((((((((((((((((((((((((((((%&&%&%&&&&&&&&&%%%&&%%%%%%%%%&&&&&%&&&@@&&&%%*")
r.say(target, "((((((((((((((((((((((((((((((((((((((((((%%&%&&&&&%%&&&&&%&&&&&&&&&%%%&@@@&%&&&&&%&&&&&&&%%(")
r.say(target, "((((((((((((((((((((((((((((((((((((((((%%%&&&&&%&&%&&&&%&&&&&&&&&&&&&&@@@&%#%%&%&&&&&&&&&&&&&%#,")
r.say(target, "(((((((((((((((((((((((((/((((((((((((%%%%%&&%%%%&%&%#&&&&&&&&&%&&&%%&&@@&&(&&&&&&&&&&&&&&&&%%%#")
r.say(target, "((((((((((((((((((((((((((((((((((((&%&&%%%%%&&&&&&&%&&&&&&&&&&%&%&&&&&%%&&&@&&&&&&&%(&&&&%%%&%&%%%#%*")
r.say(target, "(((((((((((//((((((((/((((((((((((%%&%&&&%%&&%&&&&%%&&&&&&&&&%%%&&&&&&%%%%&&&&&&&&&&&%&@@&&&&&%&&%%&&&&%%%(.")
r.say(target, "//((((((((((((((((((((((((((((((%&&%&&%&%&&&&%&%%#&&&&&&&&&%#%&&&&&&#%&&&&&&&&&&&%&@@&&&&&%&%#%%&@&&&&&&&&%%(##*")
r.say(target, "  ,///((((((((((((((((((((((((%&&%&&&%&&%%%&&&&%&&&&&&&&&%&&&&&&%#%&&&&&&&&&&&%#%&&&&%&@@&&%%%&&@@&&&&&&&&&&%&&&%(")
r.say(target, "     *//((((((((((((((((((((%&&%&&&%%%&&&&&&%#&&&&&&&&&%&&&&&&&%#%&&&&&&&&&&&%#&&&&%&&&&&&&&&%%##%%%%%&&&&&&&%(#%%%#.")
r.say(target, "        *//(((((((((((((((#&&&&&%%&&&&&&&&&(&&&&&&&&&&(%%&&&&&%&&&&&&&&&&&&&&&&&%&&&((#%%&%#(//*./#&&&%&%%%%&&%%&&%%%(")
r.say(target, "           *//(((((((((((%&&&%%&%%&&%%&&&&&&&&&&&&(&&&&&&&&((#&&&&&&&&&&&%#&&&&%&&&&(((##%%##///,.   (%%&%#%%&%%%&%%%%%%%#%")
r.say(target, "              *//(((((((&&&&&&%&%&%&&&&&&&&&&&&&&&(&&&&&&&&(&&&&&&&&&&&&&&&&&&%&&%(((##%%#((/**    ,*/((#%%&%&&%%%%%%%%(#%%#/")
r.say(target, "                 *///((&&&&&&&&&&&&&%/&&&&&&&&&&%&&&&&%%&&&&&&&&&&&&&&&&%&#((##%%##(//*    ,*/((#((%%%%%&%%%%%%#/%%%%%%.")
r.say(target, "                    */#&&&&&&&&&&&&%#&&&&&&&&&(&&&&&&&&(&%&&&&&&&&&&&&(%&&&&%%&((##%%##(//*.   .*/(((((#((/%%%&%%%%#(%%&&&&%%%%%/")
r.say(target, "                      %%%%&&&&&&&&&&&&&&&&*%%&&&&&&(%%&%&&&&&&&&&&&&&&%&&((##%%##(//*.   ,*/((#(((#((//*/&%&%#(%&&&&&&%%%%%%%%%%,")
r.say(target, "                     (####%%%&&&&&&&&&&&&&&&&&&&&&&*%%&&&&&&&&&&&&%&&&&&%%&(((##%##(//*.   .,*/(((#((((/**.  *(#&%&&@&&&&%%%%%%%&%%&%%%(")
r.say(target, "                     *((##%%%%%&&&&&&&&&&*&&&&&&%/%%&&&&&&&&&&&&%&&&&&%%&&%(((##%##((/*.    ,*/(((((((//**.  .*/(((((&&&&&&%%&&&&&%%%%%&&%%%%,")
r.say(target, "                      /((####&&&&&&&&&&%&@@@@@&&&&&&&&&&&&&&&%&&&&*&&&%(((##%%#((/*,    ,*/((####((//*,   */((((((((((%&&&&&&&&&&&%%%%&%%%%%%%(")
r.say(target, "                       *//((&&&&&&&&&%@@@@@@@@@@@&&&&&&&&&&&%%&&&%&&%(((##%%##(//,    .*/((###(((//*,   */((((((((((((((((%&&&&&&&%%%%&%%&%%&%%%%%#.")
r.say(target, "                         ./%&&&&&&&%@@@@%&%%%&@@@@&&&&&&&&%(&&&&%%&&%(((##%%##(//,.   .*/(((((#((//*,   ,//((((((((((((((((/(((#&&&&&&&%&&%%%&&%%%%%%%%#")
r.say(target, "                           /%%&&&&&&@@@&&&&&%%%&&&&&&&&&%#&&&&%%%%%(((##%%##(//*.   .,/((##(#(((/*,   ,*//(((/(((((((((//*,.*/((((#%&&&&&&&&&&&&%%%%%%#((")
r.say(target, "                           ./(##&&&&&&%%%&@@&%&&&&&&&&%#&&&&&&&((((#%%%#(//*.    ,*/((#(#(((/**.  .*/(((/(((((((((/(// .  .*//(((/*%%&&&&%%%%%%%%%%((((")
r.say(target, "                            *////(#%&&&&&&&&&&&&&&&&&%&&&&&&&&&%%&%%%%%#((/*.    ,*/(((#(((//*,.  .*//((/(((((((((((//((*,   .,*//,%%%%&%&&&&&&&&%%(##(((/")
r.say(target, "                             *//////(#%&&&&&&&&&&&&&&&&&&&&&%%&&@@@@@@@&%.   .*/(((##(((/**.   *///(((((((((((((((((/////,.***.#%&%%%%%%%%%%%%#(#&@##(((")
r.say(target, "                              ,////////(#%&&&&&&&&&&&&&&&&&&&&&&&@@@@@@@@@@&%/*/(###((((//,.   ,/(//(((((((/(((((((((///////////(%%%%%%%%%%%%%%((&@@@%#(((/")
r.say(target, "                               ,/(////////(#%&&&&&&&&&&&%&&&&&&@@@@&%#&@@@@@@@&%((#(((/**.   ,*/(((///((((((((((((/(((//////////**,*(%%#%%%%%#(&@@@@&&%#(")
r.say(target, "                                ,(/(#////////((%&&&&&&&&&@&%@@@@@%&&&&@&&&&&%&&(#(//**   .*//((/((//((((((/(((/(((////////////*,.  *%%%%%#(%&&&&&&&%#/")
r.say(target, "                                 .//((((////////((%&&&&&&&&@&@@@&&&%%%&@&%&%%(%&@@%/*,    */(((((((//((//((((((((((((/////.    ,*  ,**/&&%(#&&&%%#&%%(")
r.say(target, "                                  ,(((((((/////////((%&&%&&&&@@@@@&&&&&%#/#%%&&%&&/   ,///((((/(((((/(((((((((((((////,#%&&&&&/ ***%&&%(&@&&%%((%(.")
r.say(target, "                                     *(((/((///////////(#%&&%&&&%&@&&%(%%*%&&&&&&%&%%%%//((((/((//((/(((((/(((((((/////(&%(#%&&&&&(%&%%(%@&%%%(%%(,")
r.say(target, "                                        /((/(/////////////(%%&&&&&&&%&%(%&&&&%&%&%%&%##%@&((((((((/((//*..*/(((((/(////((#%%%&&&&&&&%(%&&&%%#((((")
r.say(target, "                                           (((//////////////((%&&&%%&%(&&&&&&&%&%%%#(#%&&@@@(((((//,.    ,*/((((////**(%&&&&&&&&(&@###&((")
r.say(target, "                                              ,(/(////(/////////(#%&&&&&&&%&%%%%%#(#%%&&&&%%&&&%#(////*    .,***/////**.  ,%&&&&&%(&@&%##((#(")
r.say(target, "                                                 /((/////(////////((#%&&%&&%%%%%(##%&&@&&&&&&&&%#%////* .,*/ (%%*,**   ,**%&&%(%@@%##((((.")
r.say(target, "                                                    /(//////(////////((#&&&%%%#(#%&&&&&&&&&&&&&&&&&&@*//*/./%&&&@ .*/*#&&#&@&%%##&(/")
r.say(target, "                                                       ((//////(////////((#%%(#%&&&&&&&%%&&&&&&&&&&%&&&&,.#%%%%%%&&&%*(%&&%#&@&%##((((")
r.say(target, "                                                          /((/////#////////((%&&&&&&&%%%&&&&&&&&&&&&&&&%%&&&&%%%%%%%#%%&&&%(&@&%#%(%%(")
r.say(target, "                                                             (((/////#////(/((#%&&&@&&%&&&&&&&&&&&&&&&&&&&%%%%%%%%##%%%%#(%@@&%%#(#(")
r.say(target, "                                                                #(/////(%////((#&&&&&@@&%&&&&&%&&&&&&&&&&&&&&%%%%#%%%%%#(&&%%(((.")
r.say(target, "                                                                   #(/////##////%&%%%&&&&@&&%%&&&&&&&&&&&&&&&&%%%%%#(&@&%%%(%%,")
r.say(target, "                                                                      #((((//(%(/%(#%%&&&&&&@@&%&&&&&&&&&&&&&&&%%%##(&@&%%%#((/")
r.say(target, "                                                                         #(((///(%%///%&&&&&&&&@@&&&&&&&&&&&&&&%%#(%&&&%%##&(")
r.say(target, "                                                                           .#(((((##/////%&&&&&&&&&@&&&&&&&&&&&&(#&&&%%#((#")
r.say(target, "                                                                               ((((#&%(/(((/#%&&&&&&&&&&&&&&&%#((%&%%%(#(")
r.say(target, "                                                                                 .((#%   ((#(//#&&&&&&&&&&&%##((((%%##&")
r.say(target, "                                                                                            (/////#&&&&&&&&%%%#(((#((.")
r.say(target, "                                                                                              .(/////#%&&%%%%#(((((/")
r.say(target, "                                                                                                 .(/////#####((((/")
r.say(target, "                                                                                                    ,(///(##((((")
r.say(target, "                                                                                                       *(/((((")
    }
  }
}
