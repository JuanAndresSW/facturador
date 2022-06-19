package dev.facturador.pointofsale.application.subdomain.command;

import dev.facturador.pointofsale.domain.subdomain.ControlOfPosData;
import dev.facturador.shared.application.commands.Command;
import lombok.Getter;

@Getter
public class ControlOfPosUpdateCommand extends Command {
    private ControlOfPosData data;


    public ControlOfPosUpdateCommand(ControlOfPosData data) {
        this.data = data;
    }

    public static class Builder {
        private ControlOfPosData data;

        public static ControlOfPosUpdateCommand.Builder getInstance() {
            return new ControlOfPosUpdateCommand.Builder();
        }

        public ControlOfPosUpdateCommand.Builder data(ControlOfPosData data) {
            this.data = data;
            return this;
        }

        public ControlOfPosUpdateCommand build() {
            return new ControlOfPosUpdateCommand(data);
        }
    }
}
