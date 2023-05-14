package org.example.utilities;

import org.example.interactions.RequestTemplate;
import org.example.interactions.ResponseTemplate;
import org.example.interactions.ResponseResult;

/**
 * Handles requests.
 */
public class RequestHandler {
    private final CommandManager commandManager;

    public RequestHandler(CommandManager commandManager) {
        this.commandManager = commandManager;
    }

    /**
     * Handles requests.
     *
     * @param request Request to be processed.
     * @return Response to request.
     */
    public ResponseTemplate handle(RequestTemplate request){
        ResponseResult responseResult = executeCommand(
                request.getCommandName(),
                request.getCommandStringArgument(),
                request.getCommandObjectArgument());
        return new ResponseTemplate(responseResult, ResponseOutputer.getAndClear());
    }

    /**
     * Executes a command from a request.
     *
     * @param command               Name of command.
     * @param commandStringArgument String argument for command.
     * @param commandObjectArgument Object argument for command.
     * @return Command execute status.
     */
    private ResponseResult executeCommand(String command, String commandStringArgument,
                                          Object commandObjectArgument) {

        switch (command) {
            case "":
                break;
            case "help":
                if (!commandManager.help(commandStringArgument, commandObjectArgument)) return ResponseResult.ERROR;
                break;
            case "info":
                if (!commandManager.info(commandStringArgument, commandObjectArgument)) return ResponseResult.ERROR;
                break;
            case "show":
                if (!commandManager.show(commandStringArgument, commandObjectArgument)) return ResponseResult.ERROR;
                break;
            case "add":
                if (!commandManager.add(commandStringArgument, commandObjectArgument)) return ResponseResult.ERROR;
                break;
            case "filter_starts_with_name":
                if (!commandManager.filterStartsWithName(commandStringArgument, commandObjectArgument)) return ResponseResult.ERROR;
                break;
            case "update":
                if (!commandManager.update(commandStringArgument, commandObjectArgument)) return ResponseResult.ERROR;
                break;
            case "remove_by_id":
                if (!commandManager.removeById(commandStringArgument, commandObjectArgument)) return ResponseResult.ERROR;
                break;
            case "remove_greater":
                if (!commandManager.removeGreater(commandStringArgument, commandObjectArgument)) return ResponseResult.ERROR;
                break;
            case "remove_lower":
                if (!commandManager.removeLower(commandStringArgument, commandObjectArgument)) return ResponseResult.ERROR;
                break;
            case "clear":
                if (!commandManager.clear(commandStringArgument, commandObjectArgument)) return ResponseResult.ERROR;
                break;
            case "save":
                if (!commandManager.save(commandStringArgument, commandObjectArgument)) return ResponseResult.ERROR;
                break;
            case "execute_script":
                if (!commandManager.executeScript(commandStringArgument, commandObjectArgument)) return ResponseResult.ERROR;
                break;
            case "add_if_max":
                if (!commandManager.addIfMax(commandStringArgument, commandObjectArgument)) return ResponseResult.ERROR;
                break;
            case "print_field_ascending_postal_address":
                if (!commandManager.printFieldAscendingPostalAddress(commandStringArgument, commandObjectArgument)) return ResponseResult.ERROR;
                break;
            case "sum_of_employees_count":
                if (!commandManager.sumOfEmployeesCount(commandStringArgument, commandObjectArgument)) return ResponseResult.ERROR;
                break;
            case "server_exit":
                if (!commandManager.serverExit(commandStringArgument, commandObjectArgument))
                    return ResponseResult.ERROR;
                return ResponseResult.SERVER_EXIT;
            default:
                ResponseOutputer.appendLn("Command '" + command + "' was not found. Try to write 'help' for more info.");
                return ResponseResult.ERROR;
        }
        return ResponseResult.OK;
    }
}
