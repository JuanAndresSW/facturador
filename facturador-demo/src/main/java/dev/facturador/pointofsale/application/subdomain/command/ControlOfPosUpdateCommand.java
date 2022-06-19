package dev.facturador.pointofsale.application.subdomain.command;

import dev.facturador.global.application.commands.Command;
import dev.facturador.pointofsale.domain.subdomain.PosControlData;
import lombok.Getter;

@Getter
public class ControlOfPosUpdateCommand extends Command {
    private final PosControlData data;


    public ControlOfPosUpdateCommand(PosControlData data) {
        this.data = data;
    }

    public static class Builder {
        private PosControlData data;

        public static ControlOfPosUpdateCommand.Builder getInstance() {
            return new ControlOfPosUpdateCommand.Builder();
        }

        public ControlOfPosUpdateCommand.Builder data(PosControlData data) {
            this.data = data;
            return this;
        }

        public ControlOfPosUpdateCommand build() {
            return new ControlOfPosUpdateCommand(data);
        }
    }
}
